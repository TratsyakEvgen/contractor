package by.bysend.contractor.service.impl;

import by.bysend.contractor.model.dto.AuthDTO;
import by.bysend.contractor.model.dto.TokenDTO;
import by.bysend.contractor.model.entity.AuthData;
import by.bysend.contractor.model.entity.name.RoleName;
import by.bysend.contractor.repository.AuthDataRepository;
import by.bysend.contractor.security.JwtProvider;
import by.bysend.contractor.service.TokenService;
import by.bysend.contractor.service.exception.ErrorCode;
import by.bysend.contractor.service.exception.ServiceException;
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
                .orElseThrow(
                        () -> new ServiceException(String.format("Login %s not found", login), ErrorCode.USER_NO_EXISTS)
                );

        if (!passwordEncoder.matches(authDTO.getPassword(), authData.getPassword())) {
            throw new ServiceException("Incorrect password", ErrorCode.INCORRECT_PASSWORD);
        }

        long id = authData.getId();
        RoleName roleName = authData.getRole().getRoleName();
        return TokenDTO.builder()
                .access(jwtProvider.generateAccessToken(id, roleName.toString()))
                .refresh(jwtProvider.generateRefreshToken(id, roleName.toString()))
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
