package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "client_contacts")
@Getter
@Setter
public class ClientContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long clientContactId;
    private String phoneNumber;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientContact that = (ClientContact) object;
        return clientContactId == that.clientContactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientContactId);
    }
}
