package by.bysend.contractor.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NotNull(message = "CreateOrder must be not null")
public class CreateOrder {
    private String description;
    private String loadingPlace;
    private String uploadingPlace;
    private LocalDate loadingDate;
    private String customDeparture;
    private LocalDate uploadingDate;
    private String customDestination;
    private long codeTNVD;
    private String name;
    private String weight;
    private BigDecimal cost;
    private String dimensions;
    private String packing;
    private String nonTariffRegulation;
    private Boolean stacking;
    private String temperature;
    private String ADR;
    private BigDecimal clientRate;
    private String info;
    @NotNull(message = "Client full name must be not null")
    private String clientFullName;
    @NotNull(message = "Client number phone must be not null")
    @Pattern(regexp = "\\d{12}", message = "Incorrect number phone")
    private String clientNumberPhone;
}
