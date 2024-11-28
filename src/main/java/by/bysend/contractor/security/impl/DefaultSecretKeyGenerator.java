package by.bysend.contractor.security.impl;

import by.bysend.contractor.security.SecretKeyGenerator;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class DefaultSecretKeyGenerator implements SecretKeyGenerator {
    private final SecretKey accessSecretKey = Jwts.SIG.HS256.key().build();
    private final SecretKey refreshSecretKey = Jwts.SIG.HS256.key().build();

    @Override
    public SecretKey getAccessSecretKey() {
        return accessSecretKey;
    }

    @Override
    public SecretKey getRefreshSecretKey() {
        return refreshSecretKey;
    }
}
