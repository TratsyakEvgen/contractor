package by.bysend.contractor.service;

import by.bysend.contractor.dto.response.ResponseReport;
import by.bysend.contractor.model.FileReport;
import jakarta.validation.constraints.NotNull;

import java.io.InputStream;

public interface ReportService {
    FileReport get(long clientId, long meetingId);

    ResponseReport create(long clientId, long meetingId,
                          @NotNull(message = "Filename must not be null") String filename,
                          @NotNull(message = "InputStream must not be null") InputStream inputStream);

    void delete(long clientId, long meetingId);
}
