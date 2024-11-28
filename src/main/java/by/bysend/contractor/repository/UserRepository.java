package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long userId);

    @EntityGraph(attributePaths = "role")
    Optional<User> findByLogin(String login);
}
