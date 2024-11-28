package by.bysend.contractor.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseTokens {
    private String access;
    private String refresh;
}
