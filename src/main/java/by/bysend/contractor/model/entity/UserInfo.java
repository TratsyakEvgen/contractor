package by.bysend.contractor.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String email;
    @OneToOne
    @MapsId
    private User user;
}
