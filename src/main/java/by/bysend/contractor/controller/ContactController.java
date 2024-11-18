package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.CreateContact;
import by.bysend.contractor.dto.request.UpdateContact;
import by.bysend.contractor.dto.response.ResponseContact;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients/{clientId}/contacts")
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "Получить все контакты", tags = "contacts")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping
    public List<ResponseContact> getAll(@PathVariable long clientId) {
        return contactService.getAll(clientId);
    }

    @Operation(summary = "Создание контакта", tags = "contacts")
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
    public ResponseContact create(@PathVariable long clientId, @RequestBody CreateContact createContact) {
        return contactService.create(clientId, createContact);
    }

    @Operation(summary = "Обновление контакта", tags = "contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или контакт не найден",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PutMapping("/{contactId}")
    public ResponseContact update(@PathVariable long clientId, @PathVariable long contactId, @RequestBody UpdateContact updateContact) {
        return contactService.update(clientId, contactId, updateContact);
    }

    @Operation(summary = "Удаление контакта", tags = "contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{contactId}")
    public void delete(@PathVariable long clientId, @PathVariable long contactId) {
        contactService.delete(clientId, contactId);
    }
}