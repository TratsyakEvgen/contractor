package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "calls")
@Getter
@Setter
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long callId;
    private LocalDate localDate;
    private String result;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Call call = (Call) object;
        return callId == call.callId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(callId);
    }
}
