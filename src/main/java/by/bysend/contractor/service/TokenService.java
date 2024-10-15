package by.bysend.contractor.service;

import by.bysend.contractor.dto.AuthDTO;
import by.bysend.contractor.dto.TokenDTO;

public interface TokenService {
    TokenDTO createNewTokens(AuthDTO authDTO);

    String updateAccessToken(String refreshToken);
}
