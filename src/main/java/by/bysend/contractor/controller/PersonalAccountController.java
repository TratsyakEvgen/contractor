package by.bysend.contractor.controller;

import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.dto.response.ResponsePersonalAccount;
import by.bysend.contractor.service.PersonalAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonalAccountController {
    private final PersonalAccountService personalAccountService;

    @Operation(summary = "Личный кабинет пользователя", tags = "personal account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @GetMapping("users/{userId}/personalAccount")
    public ResponsePersonalAccount getPersonalAccount(@PathVariable long userId) {
        return personalAccountService.getPersonalAccount(userId);
    }
}
