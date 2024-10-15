package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.account.status.AccountTypeDTO;
import by.bysend.contractor.model.entity.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountTypeMapper {
    AccountTypeDTO getAccountTypeDto(AccountType accountType);
}
