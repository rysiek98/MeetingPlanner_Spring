package com.MeetingPlanner.calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public List<Calendar> findAll(){
        return calendarRepository.findAll();
    }

    public Calendar addCalendar(Calendar calendar){
        calendarRepository.save(calendar);
        return calendar;
    }
}
