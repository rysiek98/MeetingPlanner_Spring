package com.MeetingPlanner.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendars")
    public List<Calendar> getCalendars(){
        return calendarService.getCalendars();
    }
}
