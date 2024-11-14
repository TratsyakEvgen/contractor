package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@NotNull(message = "UpdateCall must be not null")
public class UpdateCall {
    @NotNull(message = "Date must be not null")
    private LocalDate localDate;
    @NotBlank(message = "Result must be not null")
    private String result;
}
