package com.MeetingPlanner.calendar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public void addCalendar(Calendar calendar){

    }

    public List<Calendar> getAllCalendars(){
        return calendarRepository.findAll();
    }
}
