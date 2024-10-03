package by.bysend.contractor.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String bearer = request.getHeader("Authorization");
        try {
            if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
                String token = bearer.substring(7);

                Claims claims = jwtProvider.getAccessClaims(token);
                long id = claims.get("id", Long.class);
                String role = claims.get("role", String.class);

                SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(id, role));
            }
        } catch (SignatureException e) {
            log.warn("No valid token", e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}