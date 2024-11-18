package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseReport;
import by.bysend.contractor.model.entity.Report;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportMapper {

    ResponseReport toResponseReport(Report report);

    Report toReport(ResponseReport responseReport);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Report partialUpdate(ResponseReport responseReport, @MappingTarget Report report);
}