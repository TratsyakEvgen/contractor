package by.bysend.contractor.security.impl;

import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.User;
import by.bysend.contractor.repository.ClientRepository;
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
public class ClientSecurityService extends AbstractSecurityService {
    private final ClientRepository clientRepository;

    @Override
    protected boolean isAuthorized(Authentication authentication, RequestAuthorizationContext context) {
        try {
            return Optional.ofNullable(context.getVariables().get("clientId"))
                    .flatMap(stringId -> clientRepository.findClientById(Long.parseLong(stringId)))
                    .map(Client::getUser)
                    .map(User::getId)
                    .map(id -> id.equals(Long.parseLong(authentication.getName())))
                    .orElseThrow(() ->
                            new SecurityServiceException("Not found client " + context.getRequest().getRequestURI())
                    );
        } catch (NumberFormatException | SecurityServiceException e) {
            throw new AccessDeniedException(e.getMessage());
        }
    }
}
