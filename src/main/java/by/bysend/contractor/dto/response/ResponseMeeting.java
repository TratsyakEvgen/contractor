package by.bysend.contractor.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseMeeting {
    private long id;
    private LocalDate localDate;
    private String result;
}