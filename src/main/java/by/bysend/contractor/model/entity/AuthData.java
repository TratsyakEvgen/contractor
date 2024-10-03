package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthData {
    @Id
    private Long id;
    private String login;
    private String password;
    @OneToOne
    @MapsId("id")
    private User user;
    @ManyToOne
    private Role role;

}
