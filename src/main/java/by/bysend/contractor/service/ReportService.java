package by.bysend.contractor.service;

import by.bysend.contractor.dto.response.ResponseReport;

public interface ReportService {
    ResponseReport get(long reportId);
}
