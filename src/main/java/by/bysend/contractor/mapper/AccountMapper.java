package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseAccount;
import by.bysend.contractor.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AccountTypeMapper.class})
public interface AccountMapper {
    ResponseAccount getResponseAccount(Account account);
}
