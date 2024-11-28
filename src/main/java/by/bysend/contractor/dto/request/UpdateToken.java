package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull(message = "Tokens must be not null")
public class UpdateToken {
    @NotBlank(message = "Access must be not blank")
    private String access;
    @NotBlank(message = "Refresh must be not blank")
    private String refresh;
}
