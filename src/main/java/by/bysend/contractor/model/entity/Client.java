package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientStatus clientStatus;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Meeting> meetings;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Call> calls;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ClientContact> contacts;

}
