package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.response.ResponseReport;
import by.bysend.contractor.repository.ReportRepository;
import by.bysend.contractor.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultReportService implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public ResponseReport get(long reportId) {
        return null;
    }
}
