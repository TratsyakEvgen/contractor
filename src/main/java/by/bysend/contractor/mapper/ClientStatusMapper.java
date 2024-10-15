package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.client.status.ClientStatusDTO;
import by.bysend.contractor.model.entity.ClientStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientStatusMapper {
    ClientStatusDTO getClientStatusDto(ClientStatus clientStatus);
}
