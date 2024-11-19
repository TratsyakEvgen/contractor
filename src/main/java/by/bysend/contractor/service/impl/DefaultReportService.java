package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.response.ResponseReport;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.mapper.ReportMapper;
import by.bysend.contractor.model.FileReport;
import by.bysend.contractor.model.entity.Meeting;
import by.bysend.contractor.model.entity.Report;
import by.bysend.contractor.repository.ReportRepository;
import by.bysend.contractor.service.MeetingEntityService;
import by.bysend.contractor.service.ReportFileService;
import by.bysend.contractor.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.InputStream;
import java.util.Optional;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class DefaultReportService implements ReportService {
    private final MeetingEntityService meetingEntityService;
    private final ReportRepository reportRepository;
    private final ReportFileService reportFileService;
    private final ReportMapper reportMapper;


    @Override
    public FileReport get(long clientId, long meetingId) {
        Meeting meeting = meetingEntityService.getMeeting(clientId, meetingId);
        Report report = getReport(clientId, meetingId, meeting);
        return reportFileService.get(report);
    }

    @Override
    public ResponseReport create(long clientId, long meetingId, String filename, InputStream inputStream) {
        Meeting meeting = meetingEntityService.getMeeting(clientId, meetingId);
        Optional.ofNullable(meeting.getReport())
                .ifPresent(report -> {
                    throw new ServiceException(
                            String.format("Report for meeting with id %d for client with id %d already exists", meetingId, clientId),
                            ErrorCode.ENTITY_ALREADY_EXISTS
                    );
                });
        Report report = new Report().setFileName(filename);
        reportRepository.save(report);
        meeting.setReport(report);
        reportFileService.save(report, inputStream);
        return reportMapper.toResponseReport(report);
    }

    @Override
    public void delete(long clientId, long meetingId) {
        Meeting meeting = meetingEntityService.getMeeting(clientId, meetingId);
        Report report = getReport(clientId, meetingId, meeting);
        meeting.setReport(null);
        reportFileService.delete(report);
    }

    private Report getReport(long clientId, long meetingId, Meeting meeting) {
        return Optional.ofNullable(meeting.getReport())
                .orElseThrow(() -> new ServiceException(
                                String.format("Report for meeting with id %d for client with id %d not found", meetingId, clientId),
                                ErrorCode.ENTITY_NOT_EXISTS
                        )
                );
    }
}
