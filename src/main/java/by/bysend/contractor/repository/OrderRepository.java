package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "reward")
    List<Order> findAllByClientIn(List<Client> clients);
}