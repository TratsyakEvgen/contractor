package by.bysend.contractor.security;

import by.bysend.contractor.dto.request.AuthenticationData;
import by.bysend.contractor.dto.request.UpdateToken;
import by.bysend.contractor.dto.response.ResponseTokens;
import jakarta.validation.Valid;

public interface TokenService {
    ResponseTokens create(@Valid AuthenticationData authenticationData);

    ResponseTokens update(@Valid UpdateToken updateToken);
}
