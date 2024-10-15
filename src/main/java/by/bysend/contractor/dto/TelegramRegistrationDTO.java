package by.bysend.contractor.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TelegramRegistrationDTO {
    @Pattern(regexp = "^[А-ЯЁ][a-яА-ЯЁё-]+\\s[А-ЯЁ][a-яА-ЯЁё-]+\\s[А-ЯЁ][a-яА-ЯЁё-]+$", message = "Неверный формат ФИО")
    private String fullName;
    @NotNull(message = "Неверный ID Telegram")
    @Min(value = 1, message = "Неверный ID Telegram")
    private Long telegramId;
    @NotBlank(message = "Адрес регистрации не должен быть пустым")
    private String registrationAddress;
    @NotBlank(message = "Адрес не должен быть пустым")
    private String realAddress;
    @NotEmpty(message = "Неверный формат номера паспорта")
    @Pattern(regexp = "^(AB|BM|HB|KH|MP|MC|KB|PP|SP|DP)\\d{7}$", message = "Неверный формат номера паспорта")
    private String passportNumber;
    @NotEmpty(message = "Неверный формат индификационного номера паспорта")
    @Pattern(regexp = "^[1-6]\\d{6}[ABCKEMH]\\d{3}(PB|BA|BI)\\d$", message = "Неверный формат индификационного номера паспорта")
    private String passportId;
    @NotEmpty(message = "Неверный формат номера социального страхования")
    @Pattern(regexp = "^[1-6]\\d{6}[ABCKEMH]\\d{3}(PB|BA|BI)\\d$", message = "Неверный формат номера социального страхования")
    private String insuranceId;
    @NotEmpty(message = "Неверный формат номера телефона")
    @Pattern(regexp = "^\\+[0-9]{12}$", message = "Неверный формат номера телефона")
    private String phoneNumber;
    @NotEmpty(message = "Неверный формат email")
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Неверный формат email")
    private String email;

}
