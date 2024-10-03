package by.bysend.contractor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDTO {
    private int status;
    private String message;
}
