package by.bysend.contractor.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @ManyToOne
    private Role role;
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private UserDetails userDetails;

}
