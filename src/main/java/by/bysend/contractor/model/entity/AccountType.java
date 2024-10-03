package by.bysend.contractor.model.entity;

import by.bysend.contractor.model.entity.name.AccountTypeName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "account_types")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AccountTypeName typeName;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;

}
