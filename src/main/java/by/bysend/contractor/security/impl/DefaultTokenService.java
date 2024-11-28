package by.bysend.contractor.security.impl;

import by.bysend.contractor.dto.request.AuthenticationData;
import by.bysend.contractor.dto.request.UpdateToken;
import by.bysend.contractor.dto.response.ResponseTokens;
import by.bysend.contractor.model.entity.User;
import by.bysend.contractor.repository.UserRepository;
import by.bysend.contractor.security.SecretKeyGenerator;
import by.bysend.contractor.security.TokenService;
import by.bysend.contractor.security.exception.SecurityServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class DefaultTokenService implements TokenService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SecretKeyGenerator secretKeyGenerator;
    private final Map<String, ResponseTokens> tokenCache = new ConcurrentHashMap<>();

    @Override
    public ResponseTokens create(AuthenticationData authenticationData) {
        User user = userRepository.findByLogin(authenticationData.getLogin())
                .orElseThrow(() -> new SecurityServiceException("Incorrect login"));
        if (!passwordEncoder.matches(authenticationData.getPassword(), user.getPassword())) {
            throw new SecurityServiceException("Incorrect password");
        }
        return createTokens(String.valueOf(user.getId()), user.getRole().getRoleName());
    }

    @Override
    public ResponseTokens update(UpdateToken updateToken) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKeyGenerator.getAccessSecretKey())
                .build()
                .parseSignedClaims(updateToken.getAccess())
                .getPayload();
        Optional.ofNullable(tokenCache.remove(claims.getSubject()))
                .filter(tokens -> tokens.getAccess().equals(updateToken.getAccess()))
                .map(ResponseTokens::getRefresh)
                .filter(refresh -> updateToken.getRefresh().equals(refresh))
                .filter(refresh -> Jwts.parser()
                        .verifyWith(secretKeyGenerator.getRefreshSecretKey())
                        .build()
                        .parseSignedClaims(refresh)
                        .getPayload()
                        .getExpiration()
                        .after(new Date()))
                .orElseThrow(() -> new SecurityServiceException("Incorrect tokens"));
        return createTokens(claims.getSubject(), claims.get("scope", String.class));
    }

    private ResponseTokens createTokens(String userId, String roleName) {
        String access = Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .signWith(secretKeyGenerator.getAccessSecretKey())
                .subject(userId)
                .claim("scope", roleName)
                .compact();

        String refresh = Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .signWith(secretKeyGenerator.getRefreshSecretKey())
                .compact();


        ResponseTokens responseTokens = new ResponseTokens().setAccess(access).setRefresh(refresh);
        tokenCache.put(userId, responseTokens);
        return responseTokens;
    }
}
