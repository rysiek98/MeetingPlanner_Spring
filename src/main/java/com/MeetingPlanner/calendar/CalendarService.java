package com.MeetingPlanner.calendar;

import com.MeetingPlanner.MeetingPlannerLogic;
import com.MeetingPlanner.meeting.Meeting;
import com.MeetingPlanner.meeting.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    List<Calendar> findAllCalendars(){

        return Optional.ofNullable(calendarRepository.findAll()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    Calendar add(Calendar calendar){
        Meeting.countDuration(calendar.getMeetings());
        return calendarRepository.save(calendar);
    }

    Optional<Calendar> findById(long id) {

       return Optional.ofNullable(calendarRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    String deleteById(long id) {
            calendarRepository.delete(findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
            return "DELETED";
    }

    @Transactional
    Calendar updateById(Calendar calendar) {

            Calendar updateCalendar = findById(calendar.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            updateCalendar.setWorkBegin(calendar.getWorkBegin());
            updateCalendar.setWorkEnd(calendar.getWorkEnd());
            updateCalendar.setData(calendar.getData());
            MeetingService.updateMeetings(updateCalendar.getMeetings(),calendar.getMeetings());
            return calendarRepository.save(updateCalendar);

    }

    public List<Meeting> planNewMeeting(LocalTime duration, Long id1, Long id2) {

        return MeetingPlannerLogic.newMeetingTime(calendarRepository.findById(id1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
           ,calendarRepository.findById(id2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), duration.getMinute());
    }
}
