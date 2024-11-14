package by.bysend.contractor.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseClient {
    private long id;
    private String name;
    private List<ResponseContact> contacts;
    private List<ResponseCall> calls;
    private List<ResponseMeeting> meetings;
    private ResponseClientStatus clientStatus;

}
