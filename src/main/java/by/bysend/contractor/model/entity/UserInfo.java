package by.bysend.contractor.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    @Id
    private Long id;
    private Long telegramId;
    private String registrationAddress;
    private String realAddress;
    private String passportNumber;
    private String passportId;
    private String insuranceId;
    private String phoneNumber;
    private String Email;
    @OneToOne
    @MapsId("id")
    private User user;
}
