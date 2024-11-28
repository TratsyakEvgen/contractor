package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.CreateMeeting;
import by.bysend.contractor.dto.request.UpdateMeeting;
import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.dto.response.ResponseMeeting;
import by.bysend.contractor.dto.response.ResponseMeetingWithReport;
import by.bysend.contractor.service.MeetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients/{clientId}/meetings")
@SecurityRequirement(name = "Bearer Authentication")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
})
public class MeetingController {
    private final MeetingService meetingService;

    @Operation(summary = "Получить все встречи", tags = "meetings",
            description = "Предоставляет отсортированный по времени список встреч"
    )
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @GetMapping
    public List<ResponseMeeting> getAll(@PathVariable long clientId) {
        return meetingService.getAll(clientId);
    }

    @Operation(summary = "Получение встречи", tags = "meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @GetMapping("{meetingId}")
    public ResponseMeetingWithReport get(@PathVariable long clientId, @PathVariable long meetingId) {
        return meetingService.get(clientId, meetingId);
    }

    @Operation(summary = "Создание встречи", tags = "meetings")
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
    public ResponseMeeting create(@PathVariable long clientId, @RequestBody CreateMeeting createMeeting) {
        return meetingService.create(clientId, createMeeting);
    }

    @Operation(summary = "Обновление встречи", tags = "meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Невалидный запрос",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @PutMapping("/{meetingId}")
    public ResponseMeeting update(@PathVariable long clientId, @PathVariable long meetingId, @RequestBody UpdateMeeting updateMeeting) {
        return meetingService.update(clientId, meetingId, updateMeeting);
    }

    @Operation(summary = "Удаление встречи", tags = "meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Клиент или встреча не найдены",
                    content = @Content(schema = @Schema(implementation = ResponseError.class)))
    })
    @DeleteMapping("/{meetingId}")
    public void delete(@PathVariable long clientId, @PathVariable long meetingId) {
        meetingService.delete(clientId, meetingId);
    }
}
