package by.bysend.contractor.repository;

import by.bysend.contractor.dto.request.ClientFilter;
import by.bysend.contractor.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client as c where " +
            "(:#{#clientFilter.name} is null or c.name ilike %:#{#clientFilter.name}%) " +
            "and (:#{#clientFilter.statusId} is null or c.clientStatus = :#{#clientFilter.statusId}) " +
            "and c.user.id = :userId")
    Page<Client> findAllByUserUseFilter(long userId, Pageable pageable, ClientFilter clientFilter);

    @EntityGraph(attributePaths = {"contacts", "clientStatus"})
    @Query("from Client client left join fetch client.calls call left join fetch client.meetings meetting " +
            "where client.id in(:listId) " +
            "and call.localDateTime = (select max(c.localDateTime) from Call c where c.client.id = client.id) " +
            "and meetting.localDate = (select max(m.localDate) from Meeting m where m.client.id = client.id)")
    List<Client> findAllByIdInWithMaxDateAction(List<Long> listId);

    @EntityGraph(attributePaths = {"meetings", "orders"})
    List<Client> findAllByUserId(long userId);

    @EntityGraph(attributePaths = "user")
    Optional<Client> findClientById(long id);
}