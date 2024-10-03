package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private TransportationType transportationType;
    private String transportType;
    private String loadingPlace;
    private LocalDate loadingDate;
    private String customDeparture;
    private String uploadingPlace;
    private LocalDate uploadingDate;
    private String customDestination;
    @Column(name = "code_tnvd")
    private Long codeTNVD;
    private String name;
    private String weight;
    private BigDecimal cost;
    private String dimensions;
    private String packing;
    private String NonTariffRegulation;
    private Boolean stacking;
    private String temperature;
    private String ADR;
    private BigDecimal clientRate;
    private String info;
    private String clientFullName;
    private String clientNumberPhone;





}
