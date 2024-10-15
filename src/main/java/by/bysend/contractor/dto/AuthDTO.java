package by.bysend.contractor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {
    @NotBlank(message = "Логин не должен быть пустым")
    private String login;
    @NotBlank(message = "Проль не должен быть пустым")
    private String password;
}
