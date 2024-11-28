package by.bysend.contractor.security;

import javax.crypto.SecretKey;

public interface SecretKeyGenerator {
    SecretKey getAccessSecretKey();

    SecretKey getRefreshSecretKey();
}
