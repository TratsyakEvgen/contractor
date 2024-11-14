package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
