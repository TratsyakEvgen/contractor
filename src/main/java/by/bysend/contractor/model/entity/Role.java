package by.bysend.contractor.model.entity;

import by.bysend.contractor.model.entity.name.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
