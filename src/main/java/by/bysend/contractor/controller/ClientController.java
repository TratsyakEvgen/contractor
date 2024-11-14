package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.ClientFilter;
import by.bysend.contractor.dto.response.ResponseClient;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Получить список клиентов для пользователя", tags = "clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @GetMapping("/users/{userId}/clients")
    public Page<ResponseClient> getUserClients(@PathVariable long userId,
                                               @ParameterObject Pageable pageable,
                                               @ParameterObject ClientFilter clientFilter) {
        return clientService.getUserClients(userId, pageable, clientFilter);
    }
}
