package by.bysend.contractor.dto.account;

import by.bysend.contractor.model.entity.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private long accountId;
    private String login;
    private String password;
    private AccountType accountType;
}
