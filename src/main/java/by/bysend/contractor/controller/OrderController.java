package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.CreateOrder;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients/{clientId}/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Создание запроса", tags = "orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable long clientId, @RequestBody CreateOrder createOrder) {
        orderService.create(clientId, createOrder);
    }
}
