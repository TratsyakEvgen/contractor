package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.request.CreateOrder;
import by.bysend.contractor.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toOrder(CreateOrder createOrder);
}
