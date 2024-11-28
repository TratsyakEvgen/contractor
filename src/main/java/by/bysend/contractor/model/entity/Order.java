package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    private String description;
    private String loadingPlace;
    private String uploadingPlace;
    private LocalDate loadingDate;
    private String customDeparture;
    private LocalDate uploadingDate;
    private String customDestination;
    @Column(name = "code_tnvd")
    private String codeTNVD;
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
    private String clientFullName;
    private String clientNumberPhone;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
