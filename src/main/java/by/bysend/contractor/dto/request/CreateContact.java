package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@NotNull(message = "CreateContact must be not null")
public class CreateContact {
    @Pattern(regexp = "\\d{12}")
    private String phoneNumber;
    @NotBlank(message = "Description must be not blank")
    private String description;
}
