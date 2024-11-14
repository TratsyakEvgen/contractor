package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseClientStatus;
import by.bysend.contractor.model.entity.ClientStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientStatusMapper {
    ResponseClientStatus getResponseClientStatus(ClientStatus clientStatus);
}
