package by.bysend.contractor.service;

import by.bysend.contractor.dto.request.CreateOrder;
import jakarta.validation.Valid;

public interface OrderService {
    void create(long clientId, @Valid CreateOrder createOrder);
}
