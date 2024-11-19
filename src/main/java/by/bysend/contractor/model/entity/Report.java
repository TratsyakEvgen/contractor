package by.bysend.contractor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "reports")
@Accessors(chain = true)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
}
