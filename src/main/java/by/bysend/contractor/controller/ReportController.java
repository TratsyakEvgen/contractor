package by.bysend.contractor.controller;

import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.dto.response.ResponseReport;
import by.bysend.contractor.model.FileReport;
import by.bysend.contractor.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients/{clientId}/meetings/{meetingId}/report")
@SecurityRequirement(name = "Bearer Authentication")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
})
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "Получение отчета", tags = "reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/octet-stream",
                            schema = @Schema(type = "string", format = "binary"))),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @GetMapping
    public ResponseEntity<InputStreamResource> get(@PathVariable long clientId, @PathVariable long meetingId) {
        FileReport fileReport = reportService.get(clientId, meetingId);
        return ResponseEntity.ok()
                .contentLength(fileReport.getFileSizeInBytes())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + fileReport.getFilename() + "\"")
                .body(fileReport.getInputStreamResource());

    }

    @Operation(summary = "Создание отчета", tags = "reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseReport create(@PathVariable long clientId,
                                 @PathVariable long meetingId,
                                 @NotNull(message = "File must not be null") MultipartFile file) throws IOException {
        return reportService.create(clientId, meetingId, file.getOriginalFilename(), file.getInputStream());
    }

    @Operation(summary = "Удаление отчета", tags = "reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @DeleteMapping
    public void delete(@PathVariable long clientId, @PathVariable long meetingId) {
        reportService.delete(clientId, meetingId);
    }
}
