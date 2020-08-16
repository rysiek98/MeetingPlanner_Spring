package com.MeetingPlanner.calendar;

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
            updateCalendar.setData(calendar.getData());
            updateMeetings(updateCalendar.getMeetings(),calendar.getMeetings());
            return calendarRepository.save(updateCalendar);

    }

    public List<Meeting> planNewMeeting(LocalTime duration, Long id1, Long id2) {

        return newMeetingTime(
                calendarRepository.findById(id1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                ,calendarRepository.findById(id2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                ,duration.getMinute());
    }
}
