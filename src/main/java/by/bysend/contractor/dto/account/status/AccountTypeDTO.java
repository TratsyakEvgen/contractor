package by.bysend.contractor.dto.account.status;

import by.bysend.contractor.model.entity.name.AccountTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTypeDTO {
    private Long accountId;
    private AccountTypeName typeName;
}
