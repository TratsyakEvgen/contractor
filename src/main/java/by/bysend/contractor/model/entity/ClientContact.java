package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_contacts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String description;
    @ManyToOne
    private Client client;
}
