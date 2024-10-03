package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user")
    private List<Client> clients;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthData authData;
}
