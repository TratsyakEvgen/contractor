package by.bysend.contractor.service;

import by.bysend.contractor.model.dto.AuthDTO;
import by.bysend.contractor.model.dto.TokenDTO;

public interface TokenService {
    TokenDTO createNewTokens(AuthDTO authDTO);
    String updateAccessToken(String refreshToken);
}
