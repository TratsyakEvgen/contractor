package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Client> clients;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthData authData;

}
