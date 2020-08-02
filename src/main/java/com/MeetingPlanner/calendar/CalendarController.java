package com.MeetingPlanner.calendar;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/calendar")
@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping
    public void addCalendar(@RequestBody Calendar calendar){
        calendarService.addCalendar(calendar);
    }

    @GetMapping
    public List<Calendar> getAllCalendars(){
        return calendarService.getAllCalendars();
    }
}
