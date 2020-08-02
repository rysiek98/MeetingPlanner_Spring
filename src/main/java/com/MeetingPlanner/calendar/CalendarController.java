package com.MeetingPlanner.calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping()
    private List<Calendar> findAll(){
        return calendarService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private Calendar setCalendar(@RequestBody Calendar calendar){
        return calendarService.add(calendar);
    }

}
