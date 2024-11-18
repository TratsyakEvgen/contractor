package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Meeting;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByClientIdOrderByLocalDateAsc(long clientId);

    @EntityGraph(attributePaths = "report")
    Optional<Meeting> findByClientIdAndId(long clientId, long meetingId);

    void deleteByClientIdAndId(long clientId, long meetingId);
}