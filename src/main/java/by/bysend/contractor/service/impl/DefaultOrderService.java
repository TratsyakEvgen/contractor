package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.request.CreateOrder;
import by.bysend.contractor.mapper.OrderMapper;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.Order;
import by.bysend.contractor.repository.OrderRepository;
import by.bysend.contractor.service.ClientEntityService;
import by.bysend.contractor.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ClientEntityService clientEntityService;

    @Override
    public void create(long clientId, CreateOrder createOrder) {
        Client client = clientEntityService.getClient(clientId);
        Order order = orderMapper.toOrder(createOrder);
        order.setClient(client);
        orderRepository.save(order);
    }
}
