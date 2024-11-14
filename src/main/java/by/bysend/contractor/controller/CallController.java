package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.CreateCall;
import by.bysend.contractor.dto.request.UpdateCall;
import by.bysend.contractor.dto.response.ResponseCall;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.service.CallService;
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
@RequestMapping("/clients/{clientId}/calls")
public class CallController {
    private final CallService callService;

    @Operation(summary = "Создание звонка", tags = "calls")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCall create(@PathVariable long clientId, @RequestBody CreateCall createCall) {
        return callService.create(clientId, createCall);
    }

    @Operation(summary = "Обновление звонка", tags = "calls")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или звонок не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PutMapping("/{callId}")
    public ResponseCall update(@PathVariable long clientId, @PathVariable long callId, @RequestBody UpdateCall updateCall) {
        return callService.update(clientId, callId, updateCall);
    }

    @Operation(summary = "Удаление звонка", tags = "calls")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{callId}")
    public void delete(@PathVariable long clientId, @PathVariable long callId) {
        callService.delete(clientId, callId);
    }
}
