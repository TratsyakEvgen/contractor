package by.bysend.contractor.security.impl;

import by.bysend.contractor.model.entity.User;
import by.bysend.contractor.repository.UserRepository;
import by.bysend.contractor.security.AbstractSecurityService;
import by.bysend.contractor.security.exception.SecurityServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService extends AbstractSecurityService {
    private final UserRepository userRepository;

    @Override
    protected boolean isAuthorized(Authentication authentication, RequestAuthorizationContext context) {
        try {
            return Optional.ofNullable(context.getVariables().get("userId"))
                    .flatMap(stringId -> userRepository.findById(Long.parseLong(stringId)))
                    .map(User::getId)
                    .map(id -> id.equals(Long.parseLong(authentication.getName())))
                    .orElseThrow(() ->
                            new SecurityServiceException("Not found user " + context.getRequest().getRequestURI())
                    );
        } catch (NumberFormatException | SecurityServiceException e) {
            throw new AccessDeniedException(e.getMessage());
        }
    }
}
