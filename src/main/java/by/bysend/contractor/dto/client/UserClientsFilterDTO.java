package by.bysend.contractor.dto.client;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserClientsFilterDTO {
    @Min(value = 1, message = "Id пользователя должно быть не меньше 1")
    private long userId;
    private String name;
    @Min(value = 1, message = "Id статуса клиента должно быть не меньше 1")
    private Long clientStatusId;
}
