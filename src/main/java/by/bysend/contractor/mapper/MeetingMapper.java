package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.meeting.MeetingDto;
import by.bysend.contractor.model.entity.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeetingMapper {
    MeetingDto getMeetingDto(Meeting meeting);
}