package by.bysend.contractor.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseCall {
    private long id;
    private LocalDateTime localDateTime;
    private String result;
}
