package com.MeetingPlanner.calendar;

import com.MeetingPlanner.meeting.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import static com.MeetingPlanner.meeting.Meeting.countDuration;
import static com.MeetingPlanner.meeting.MeetingService.*;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    List<Calendar> findAllCalendars(){
        return calendarRepository.findAllCalendars();
    }

    Calendar add(Calendar calendar){
        countDuration(calendar.getMeetings());
        return calendarRepository.save(calendar);
    }

    Calendar findById(long id) {
       return calendarRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    String deleteById(long id) {
            calendarRepository.delete(findById(id));
            return "DELETED";
    }

    @Transactional
    Calendar updateById(Calendar calendar) {

            Calendar updateCalendar = findById(calendar.getId());
            updateCalendar.setWorkBegin(calendar.getWorkBegin());
            updateCalendar.setWorkEnd(calendar.getWorkEnd());
            updateCalendar.setDate(calendar.getDate());
            updateMeetings(updateCalendar.getMeetings(),calendar.getMeetings());
            return calendarRepository.save(updateCalendar);

    }

    public List<Meeting> planNewMeeting(LocalTime duration, Long id1, Long id2) {

        return newMeetingTime(
                findById(id1)
                ,findById(id2)
                ,duration.getMinute());
    }
}
