package by.bysend.contractor.service.impl;

import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.model.entity.Meeting;
import by.bysend.contractor.repository.MeetingRepository;
import by.bysend.contractor.service.MeetingEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMeetingEntityService implements MeetingEntityService {
    private final MeetingRepository meetingRepository;

    @Override
    public Meeting getMeeting(long clientId, long meetingId) {
        return meetingRepository.findByClientIdAndId(clientId, meetingId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Meeting with id %d for client with id %d not found", meetingId, clientId),
                        ErrorCode.ENTITY_NOT_EXISTS)
                );
    }
}
