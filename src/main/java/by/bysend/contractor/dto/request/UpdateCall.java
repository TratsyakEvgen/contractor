package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@NotNull(message = "UpdateCall must be not null")
public class UpdateCall {
    @NotNull(message = "Time must be not null")
    private LocalDateTime localDateTime;
    @NotBlank(message = "Result must be not blank")
    private String result;
}
