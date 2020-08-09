package com.MeetingPlanner.calendar;

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
public class CalendarService {

    private final CalendarRepository calendarRepository;

    List<Calendar> findAllCalendars(){

        List<Calendar> calendarList = calendarRepository.findAllCalendars();
        try {
            if(calendarList.isEmpty()){
                throw new NoSuchElementException("No value present");
            }else
            return calendarList;
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
    }

    Calendar add(Calendar calendar){
        calendar.countDuration();
        return calendarRepository.save(calendar);
    }

    Optional<Calendar> findById(long id) {
        try {
            return Optional.ofNullable(calendarRepository.findById(id).orElseThrow());
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
    }

    String deleteById(long id) {
        try {
            Calendar deleteCalendar = findById(id).orElseThrow();
            calendarRepository.delete(deleteCalendar);
            return "DELETED";
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }
    }

    @Transactional
    Calendar updateById(Calendar calendar) {
        try {
            Calendar updateCalendar = findById(calendar.getId()).orElseThrow();
            updateCalendar.setWorkBegin(calendar.getWorkBegin());
            updateCalendar.setWorkEnd(calendar.getWorkEnd());
            updateCalendar.setData(calendar.getData());
            return calendarRepository.save(updateCalendar);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exception);
        }

    }
}
