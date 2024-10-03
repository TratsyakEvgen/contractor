package by.bysend.contractor.security.impl;

import by.bysend.contractor.security.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProviderImpl implements JwtProvider {
    private final SecretKey jwtAccessSecret = Jwts.SIG.HS256.key().build();
    private final SecretKey jwtRefreshSecret = Jwts.SIG.HS256.key().build();

    @Override
    public String generateAccessToken(long id, String role) {
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .signWith(jwtAccessSecret)
                .claim("id", id)
                .claim("role", role)
                .compact();
    }

    @Override
    public String generateRefreshToken(long id, String role) {
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(jwtRefreshSecret)
                .claim("id", id)
                .claim("role", role)
                .compact();
    }

    @Override
    public Claims getAccessClaims(String token) {
        return getClaims(token, jwtAccessSecret);
    }

    @Override
    public Claims getRefreshClaims(String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(String token, SecretKey secret) {
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
    }
}
