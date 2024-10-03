package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "client")
    private List<Order> orders;
    @ManyToOne
    private ClientStatus clientStatus;
    @OneToMany(mappedBy = "client")
    private List<Meeting> meetings;
    @OneToMany(mappedBy = "client")
    private List<Call> calls;
    @OneToMany(mappedBy = "client")
    private List<ClientContact> contacts;
}
