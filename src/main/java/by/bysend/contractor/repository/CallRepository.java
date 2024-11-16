package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CallRepository extends JpaRepository<Call, Long> {
    Optional<Call> findByClientIdAndId(long clientId, long callId);

    void deleteByClientIdAndId(long clientId, long callId);

    List<Call> findAllByClientIdOrderByLocalDateTimeAsc(long clientId);
}