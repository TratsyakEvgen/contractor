package by.bysend.contractor.dto.client;

import by.bysend.contractor.dto.call.CallDTO;
import by.bysend.contractor.dto.client.status.ClientStatusDTO;
import by.bysend.contractor.dto.contacts.ContactDTO;
import by.bysend.contractor.dto.meeting.MeetingDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserClientsDTO {
    private long clientId;
    private String name;
    private List<ContactDTO> contacts;
    private List<CallDTO> calls;
    private List<MeetingDto> meetings;
    private ClientStatusDTO clientStatus;

}
