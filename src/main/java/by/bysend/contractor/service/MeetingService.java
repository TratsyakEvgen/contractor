package by.bysend.contractor.service;

import by.bysend.contractor.dto.request.CreateMeeting;
import by.bysend.contractor.dto.request.UpdateMeeting;
import by.bysend.contractor.dto.response.ResponseMeeting;
import by.bysend.contractor.dto.response.ResponseMeetingWithReport;
import jakarta.validation.Valid;

import java.util.List;

public interface MeetingService {
    List<ResponseMeeting> getAll(long clientId);

    ResponseMeetingWithReport get(long clientId, long meetingId);

    ResponseMeeting create(long clientId, @Valid CreateMeeting createMeeting);

    ResponseMeeting update(long clientId, long meetingId, @Valid UpdateMeeting updateMeeting);

    void delete(long clientId, long meetingId);
}
