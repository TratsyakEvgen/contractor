package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;
    private String fullName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user")
    private Set<Client> clients;
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthData authData;

}
