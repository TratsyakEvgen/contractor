package by.bysend.contractor.repository;

import by.bysend.contractor.dto.client.UserClientsFilterDTO;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client as c where " +
            "(:#{#filter.name} is null or c.name ilike %:#{#filter.name}%)" +
            "and (:#{#filter.clientStatusId} is null or c.clientStatus = :#{#filter.clientStatusId})" +
            "and c.user.userId = :#{#filter.userId}")
    Page<Client> findAllUseFilter(UserClientsFilterDTO filter, Pageable pageable);

    @EntityGraph(attributePaths = {"meetings", "contacts", "clientStatus", "calls"})
    List<Client> findAllByClientIdIn(List<Long> idList);

    @EntityGraph(attributePaths = {"meetings", "orders"})
    List<Client> findAllByUser(User user);
}