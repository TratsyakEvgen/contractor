package by.bysend.contractor.dto.client;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull(message = "FilterClientDTO не должен быть null")
public class PagebaleUserClientsFilterDTO extends UserClientsFilterDTO {
    @Min(value = 0, message = "Номер страницы должен быть не меньше 0")
    private int page;
    @Min(value = 1, message = "Размер страницы должен быть не меньше 1")
    private int size;
}
