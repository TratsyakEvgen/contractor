package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.AuthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthDataRepository extends JpaRepository<AuthData, Long> {

    Optional<AuthData> findByLogin(String login);
}
