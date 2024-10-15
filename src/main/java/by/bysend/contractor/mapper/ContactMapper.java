package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.contacts.ContactDTO;
import by.bysend.contractor.model.entity.ClientContact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    ContactDTO getContactDto(ClientContact clientContact);
}
