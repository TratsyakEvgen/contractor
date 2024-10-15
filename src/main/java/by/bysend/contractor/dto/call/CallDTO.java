package by.bysend.contractor.dto.call;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CallDTO {
    private long callId;
    private LocalDate localDate;
    private String result;
}
