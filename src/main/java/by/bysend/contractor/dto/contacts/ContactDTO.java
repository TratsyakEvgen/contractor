package by.bysend.contractor.dto.contacts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {
    private long clientContactId;
    private String phoneNumber;
    private String description;
}
