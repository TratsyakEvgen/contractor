package by.bysend.contractor.service.impl;

import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.model.FileReport;
import by.bysend.contractor.model.entity.Report;
import by.bysend.contractor.service.ReportFileService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Validated
public class DefaultReportFileService implements ReportFileService {
    private final String storage;

    public DefaultReportFileService(@Value("${file.storage}") String storage) {
        this.storage = storage;
    }

    @PostConstruct
    public void createStorage() throws IOException {
        Files.createDirectories(Path.of(storage));
    }

    @Override
    public FileReport get(Report report) {
        String filename = getFilename(report);
        try {
            File file = ResourceUtils.getFile(storage + filename);
            return new FileReport().setFileSizeInBytes(file.length())
                    .setFilename(report.getFileName())
                    .setInputStreamResource(new InputStreamResource(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new ServiceException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void save(Report report, InputStream inputStream) {
        String filename = getFilename(report);
        try (inputStream; FileOutputStream fileOutputStream = new FileOutputStream(storage + filename)) {
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Report report) {
        String filename = getFilename(report);
        try {
            Files.delete(Path.of(storage + filename));
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private String getFilename(Report report) {
        String extension = FilenameUtils.getExtension(report.getFileName());
        return report.getId() + "." + extension;
    }
}
