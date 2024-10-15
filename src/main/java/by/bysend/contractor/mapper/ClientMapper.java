package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.client.UserClientsDTO;
import by.bysend.contractor.dto.page.PageDTO;
import by.bysend.contractor.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CallMapper.class, ContactMapper.class, MeetingMapper.class, ClientStatusMapper.class})
public interface ClientMapper {
    UserClientsDTO getUserClientsDto(Client client);
    @Mapping(target = "page", source = "number")
    PageDTO<UserClientsDTO> getPageDTO(Page<Client> page);
}
