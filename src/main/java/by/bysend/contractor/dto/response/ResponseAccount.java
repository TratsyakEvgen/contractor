package by.bysend.contractor.dto.response;

import by.bysend.contractor.model.entity.AccountType;
import lombok.Data;

@Data
public class ResponseAccount {
    private long id;
    private String login;
    private String password;
    private AccountType accountType;
}
