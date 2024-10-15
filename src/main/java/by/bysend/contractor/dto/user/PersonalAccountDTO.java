package by.bysend.contractor.dto.user;

import by.bysend.contractor.dto.account.AccountDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PersonalAccountDTO {
    private BigDecimal reward;
    private long meetingsCount;
    private long ordersCount;
    private List<AccountDTO> accounts;
}
