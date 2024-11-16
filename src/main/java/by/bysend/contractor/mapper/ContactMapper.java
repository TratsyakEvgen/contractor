package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.request.CreateContact;
import by.bysend.contractor.dto.request.UpdateContact;
import by.bysend.contractor.dto.response.ResponseContact;
import by.bysend.contractor.model.entity.Contact;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    ResponseContact toResponseContact(Contact contact);

    Contact toContact(CreateContact createContact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateContact updateContact, @MappingTarget Contact contact);
}
