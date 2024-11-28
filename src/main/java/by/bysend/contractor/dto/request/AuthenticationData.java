package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull(message = "AuthenticationData must be not null")
public class AuthenticationData {
    @NotBlank(message = "Login must be not blank")
    private String login;
    @NotBlank(message = "Password must be not blank")
    private String password;
}
