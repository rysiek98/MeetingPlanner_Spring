package com.MeetingPlanner.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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
        try {
            Meeting deleteMeeting = findById(id).orElseThrow();
            meetingRepository.delete(deleteMeeting);
            return "DELETED";
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
    }

    @Transactional
   Meeting updateById(Meeting meeting) {
        try {
            Meeting updateMeeting = findById(meeting.getId()).orElseThrow();
            updateMeeting.setStartTime(meeting.getStartTime());
            updateMeeting.setEndTime(meeting.getEndTime());
            return meetingRepository.save(updateMeeting);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }

    }
}
