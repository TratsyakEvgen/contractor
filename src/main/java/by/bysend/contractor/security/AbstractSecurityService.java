package by.bysend.contractor.security;

import by.bysend.contractor.security.exception.SecurityServiceException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public abstract class AbstractSecurityService implements SecurityService {
    protected Map<String, BiPredicate<Authentication, RequestAuthorizationContext>> roleDecisionMap = new HashMap<>();

    {
        roleDecisionMap.put("ROLE_ADMIN", (authentication, context) -> true);
        roleDecisionMap.put("ROLE_USER", this::isAuthorized);
    }

    @Override
    public AuthorizationDecision getDecision(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        if (authenticationSupplier == null || context == null) {
            throw new SecurityServiceException("Parameters must not be null");
        }

        Authentication authentication = authenticationSupplier.get();
        boolean granted = AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                .stream()
                .filter(s -> roleDecisionMap.containsKey(s))
                .anyMatch(s -> roleDecisionMap.get(s).test(authentication, context));

        return new AuthorizationDecision(granted);
    }

    protected abstract boolean isAuthorized(Authentication authentication, RequestAuthorizationContext context);
}
