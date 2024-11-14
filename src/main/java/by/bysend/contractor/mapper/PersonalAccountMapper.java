package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponsePersonalAccount;
import by.bysend.contractor.model.PersonalAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AccountMapper.class})
public interface PersonalAccountMapper {
    ResponsePersonalAccount getResponsePersonalAccount(PersonalAccount personalAccount);
}
