package by.bysend.contractor.service;

import by.bysend.contractor.model.FileReport;
import by.bysend.contractor.model.entity.Report;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.InputStream;

public interface ReportFileService {
    FileReport get(@NotNull(message = "Report must not be null") Report report);

    void save(@Valid Report report, @NotNull(message = "InputStream must not be null") InputStream inputStream);

    void delete(@NotNull(message = "Report must not be null") Report report);
}
