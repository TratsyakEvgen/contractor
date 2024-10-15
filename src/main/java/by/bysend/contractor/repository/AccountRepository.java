package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Account;
import by.bysend.contractor.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = {"accountType"})
    List<Account> findAllByUser(User user);
}