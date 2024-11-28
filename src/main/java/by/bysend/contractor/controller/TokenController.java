package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.AuthenticationData;
import by.bysend.contractor.dto.request.UpdateToken;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.dto.response.ResponseTokens;
import by.bysend.contractor.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @Operation(summary = "Создание токенов", tags = "tokens",
            description = "Создает access и refresh токены. Время жизни у access - 10 минут у refresh - 1 день. " +
                    "Логин и пароль для тестового пользователя - 123"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "401", description = "Неверный логин или пароль",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseTokens create(@RequestBody AuthenticationData authenticationData) {
        return tokenService.create(authenticationData);
    }

    @Operation(summary = "Создание токенов", tags = "tokens",
            description = "Возвращает новые access и refresh токены, с новым временем жизни."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "401", description = "Переданы не существующие токены или refresh токен протух",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PutMapping
    public ResponseTokens update(@RequestBody UpdateToken updateToken) {
        return tokenService.update(updateToken);
    }


}
