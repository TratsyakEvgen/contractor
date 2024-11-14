package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseClient;
import by.bysend.contractor.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CallMapper.class, ContactMapper.class, MeetingMapper.class, ClientStatusMapper.class})
public interface ClientMapper {
    ResponseClient getResponseClient(Client client);

}
