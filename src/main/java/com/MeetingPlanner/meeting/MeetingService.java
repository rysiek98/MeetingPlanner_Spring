package com.MeetingPlanner.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;


     List<Meeting> findAll(){
         try {
             if(meetingRepository.findAll().isEmpty()){
                 throw new NoSuchElementException("No value present");
             }else
                 return meetingRepository.findAll();
         }catch (Exception exception){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
         }
    }

    Meeting add(Meeting meeting){
        return meetingRepository.save(meeting);
    }

    Optional<Meeting> findById(long id) {

        try {
            return Optional.ofNullable(meetingRepository.findById(id).orElseThrow());
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
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

    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

         for (int i = updateMeetings.size(); i<meetings.size(); i++){
             meetings.remove(meetings.get(i));
         }

        for(int i = 0; i<updateMeetings.size(); i++){
            if(i<meetings.size()) {
                meetings.get(i).setStartTime(updateMeetings.get(i).getStartTime());
                meetings.get(i).setEndTime(updateMeetings.get(i).getEndTime());
            }else {
                meetings.add(updateMeetings.get(i));
            }
            meetings.get(i).countDuration();
        }

    }

    @Transactional
    Meeting updateById(Meeting meeting) {
        try {
            Meeting updateMeeting = findById(meeting.getId()).orElseThrow();
            updateMeeting.setStartTime(meeting.getStartTime());
            updateMeeting.setEndTime(meeting.getEndTime());
            updateMeeting.countDuration();
            return meetingRepository.save(updateMeeting);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
    }
}
