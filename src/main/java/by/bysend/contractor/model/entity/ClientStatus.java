package by.bysend.contractor.model.entity;

import by.bysend.contractor.model.entity.name.ClientStatusName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client_status")
@Getter
@Setter
public class ClientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long clientStatusId;
    @Enumerated(EnumType.STRING)
    private ClientStatusName status;
}
