package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.request.CreateCall;
import by.bysend.contractor.dto.request.UpdateCall;
import by.bysend.contractor.dto.response.ResponseCall;
import by.bysend.contractor.model.entity.Call;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CallMapper {
    ResponseCall toResponseCall(Call call);

    Call toCall(CreateCall createCall);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateCall updateCall, @MappingTarget Call call);
}