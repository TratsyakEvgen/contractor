package by.bysend.contractor.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String fullName;
    private String registrationAddress;
    private String realAddress;
    private String passportNumber;
    private String passportId;
    private String insuranceId;
    private String phoneNumber;
    private String Email;
    @OneToMany(mappedBy = "userDetails")
    private List<Account> accounts;
    @OneToOne
    @MapsId
    private AuthData authData;
}
