package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.response.ResponseAccountType;
import by.bysend.contractor.model.entity.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountTypeMapper {
    ResponseAccountType toResponseAccountType(AccountType accountType);
}
