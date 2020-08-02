package com.MeetingPlanner.meeting;
import com.MeetingPlanner.calendar.Calendar;
import com.MeetingPlanner.calendar.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

     List<Meeting> findAll(){
        return meetingRepository.findAll();
    }

     Meeting add(Meeting meeting){
        return meetingRepository.save(meeting);
    }
}
