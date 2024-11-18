package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}