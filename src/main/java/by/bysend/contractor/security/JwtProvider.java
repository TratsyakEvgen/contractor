package by.bysend.contractor.security;


import io.jsonwebtoken.Claims;

public interface JwtProvider {
    String generateAccessToken(long id, String role);

    String generateRefreshToken(long id, String role);

    Claims getAccessClaims(String token);

    Claims getRefreshClaims(String token);
}
