package by.bysend.contractor.model.entity;

import by.bysend.contractor.model.entity.name.ClientStatusName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ClientStatusName status;
    @OneToMany(mappedBy = "clientStatus")
    private List<Client> clients;
}
