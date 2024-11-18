package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.request.CreateMeeting;
import by.bysend.contractor.dto.request.UpdateMeeting;
import by.bysend.contractor.dto.response.ResponseMeeting;
import by.bysend.contractor.dto.response.ResponseMeetingWithReport;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.mapper.MeetingMapper;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.Meeting;
import by.bysend.contractor.repository.MeetingRepository;
import by.bysend.contractor.service.ClientEntityService;
import by.bysend.contractor.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultMeetingService implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final ClientEntityService clientEntityService;
    private final MeetingMapper meetingMapper;

    @Override
    public List<ResponseMeeting> getAll(long clientId) {
        return meetingRepository.findAllByClientIdOrderByLocalDateAsc(clientId)
                .stream()
                .map(meetingMapper::toResponseMeeting)
                .toList();
    }

    @Override
    public ResponseMeetingWithReport get(long clientId, long meetingId) {
        Meeting meeting = findByClientIdAndId(clientId, meetingId);
        return meetingMapper.toResponseMeetingWithReport(meeting);
    }

    @Override
    public ResponseMeeting create(long clientId, CreateMeeting createMeeting) {
        Client client = clientEntityService.getClient(clientId);
        Meeting meeting = meetingMapper.toMeeting(createMeeting);
        meeting.setClient(client);
        meetingRepository.save(meeting);
        return meetingMapper.toResponseMeeting(meeting);
    }

    @Override
    public ResponseMeeting update(long clientId, long meetingId, UpdateMeeting updateMeeting) {
        Meeting meeting = findByClientIdAndId(clientId, meetingId);
        meetingMapper.update(updateMeeting, meeting);
        meetingRepository.save(meeting);
        return meetingMapper.toResponseMeeting(meeting);
    }

    @Override
    public void delete(long clientId, long meetingId) {
        meetingRepository.deleteByClientIdAndId(clientId, meetingId);
    }

    private Meeting findByClientIdAndId(long clientId, long meetingId) {
        return meetingRepository.findByClientIdAndId(clientId, meetingId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Meeting with id %d for client with id %d not found", meetingId, clientId),
                        ErrorCode.ENTITY_NOT_EXISTS)
                );
    }
}
