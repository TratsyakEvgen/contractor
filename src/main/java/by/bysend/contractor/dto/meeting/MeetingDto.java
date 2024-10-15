package by.bysend.contractor.dto.meeting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MeetingDto {
    private Long id;
    private LocalDate localDate;
    private String result;
}