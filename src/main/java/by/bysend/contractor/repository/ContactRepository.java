package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByClientId(long clientId);

    Optional<Contact> findByClientIdAndId(long clientId, long contactId);

    void deleteByClientIdAndId(long clientId, long contactId);
}