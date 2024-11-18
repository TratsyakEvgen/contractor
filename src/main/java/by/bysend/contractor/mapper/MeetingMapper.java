package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.request.CreateMeeting;
import by.bysend.contractor.dto.request.UpdateMeeting;
import by.bysend.contractor.dto.response.ResponseMeeting;
import by.bysend.contractor.dto.response.ResponseMeetingWithReport;
import by.bysend.contractor.model.entity.Meeting;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ReportMapper.class})
public interface MeetingMapper {
    ResponseMeeting toResponseMeeting(Meeting meeting);

    ResponseMeetingWithReport toResponseMeetingWithReport(Meeting meeting);

    Meeting toMeeting(CreateMeeting createMeeting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateMeeting updateMeeting, @MappingTarget Meeting meeting);
}