package by.bysend.contractor.model;

import by.bysend.contractor.model.entity.Account;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class PersonalAccount {
    private BigDecimal reward;
    private long meetingsCount;
    private long ordersCount;
    private List<Account> accounts;
}
