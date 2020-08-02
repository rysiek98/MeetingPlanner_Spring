package com.MeetingPlanner.meeting;

import com.MeetingPlanner.calendar.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    Optional<Meeting> findById(long id) {
        return meetingRepository.findById(id);
    }

    String deleteById(long id) {
        if(meetingRepository.existsById(id)) {
            meetingRepository.deleteById(id);
            return "Delete";
        }
        return "Not found";
    }

    Meeting updateById(long id, Meeting meeting) {
        return meetingRepository.save(meeting);
    }
}
