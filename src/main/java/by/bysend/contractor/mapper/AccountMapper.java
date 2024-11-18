package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseAccount;
import by.bysend.contractor.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AccountTypeMapper.class})
public interface AccountMapper {
    ResponseAccount toResponseAccount(Account account);
}
