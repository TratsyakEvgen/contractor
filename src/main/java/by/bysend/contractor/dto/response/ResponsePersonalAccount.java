package by.bysend.contractor.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponsePersonalAccount {
    private BigDecimal reward;
    private long meetingsCount;
    private long ordersCount;
    private List<ResponseAccount> accounts;
}
