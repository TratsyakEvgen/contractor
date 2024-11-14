package by.bysend.contractor.controller;

import by.bysend.contractor.dto.AuthDTO;
import by.bysend.contractor.dto.TokenDTO;
import by.bysend.contractor.service.TokenService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
@Hidden
public class TokenController {
    private final TokenService tokenService;

    @PostMapping
    private TokenDTO createToken(@Valid @RequestBody AuthDTO authDTO) {
        return tokenService.createNewTokens(authDTO);
    }

    @PatchMapping
    private String updateAccessToken(@Valid @RequestBody String refreshToken) {
        return tokenService.updateAccessToken(refreshToken);
    }
}
