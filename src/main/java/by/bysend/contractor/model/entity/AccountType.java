package by.bysend.contractor.model.entity;

import by.bysend.contractor.model.entity.name.AccountTypeName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "account_types")
@Getter
@Setter
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private AccountTypeName typeName;
}
