package by.bysend.contractor.service;

import by.bysend.contractor.model.entity.Meeting;

public interface MeetingEntityService {
    Meeting getMeeting(long clientId, long meetingId);
}
