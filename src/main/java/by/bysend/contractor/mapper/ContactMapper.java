package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseContact;
import by.bysend.contractor.model.entity.ClientContact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    ResponseContact getResponseContact(ClientContact clientContact);
}
