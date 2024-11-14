package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.AuthDTO;
import by.bysend.contractor.dto.TokenDTO;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.model.entity.AuthData;
import by.bysend.contractor.repository.AuthDataRepository;
import by.bysend.contractor.security.JwtProvider;
import by.bysend.contractor.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final AuthDataRepository authDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public TokenDTO createNewTokens(AuthDTO authDTO) {
        String login = authDTO.getLogin();

        AuthData authData = authDataRepository.findByLogin(login)
                .orElseThrow(() -> new ServiceException("Login " + login + " not found", ErrorCode.ENTITY_NOT_EXISTS));

        if (!passwordEncoder.matches(authDTO.getPassword(), authData.getPassword())) {
            throw new ServiceException("Incorrect password", ErrorCode.INCORRECT_PASSWORD);
        }

        long id = authData.getId();
        String roleName = authData.getRole().getRole();
        return TokenDTO.builder()
                .access(jwtProvider.generateAccessToken(id, roleName))
                .refresh(jwtProvider.generateRefreshToken(id, roleName))
                .build();
    }

    @Override
    public String updateAccessToken(String refreshToken) {
        Claims refreshClaims = jwtProvider.getRefreshClaims(refreshToken);
        long id = refreshClaims.get("id", Long.class);
        String role = refreshClaims.get("role", String.class);
        return jwtProvider.generateAccessToken(id, role);
    }
}
