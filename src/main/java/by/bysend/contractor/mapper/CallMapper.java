package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.call.CallDTO;
import by.bysend.contractor.model.entity.Call;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CallMapper {
    CallDTO getCallDto(Call call);
}