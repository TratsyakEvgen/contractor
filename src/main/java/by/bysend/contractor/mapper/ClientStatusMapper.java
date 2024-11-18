package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseClientStatus;
import by.bysend.contractor.model.entity.ClientStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientStatusMapper {
    ResponseClientStatus toResponseClientStatus(ClientStatus clientStatus);
}
