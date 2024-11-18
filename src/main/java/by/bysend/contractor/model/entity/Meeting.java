package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "meetings")
@Getter
@Setter
@ToString(exclude = "client")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate localDate;
    private String result;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Report report;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Meeting meeting = (Meeting) object;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
