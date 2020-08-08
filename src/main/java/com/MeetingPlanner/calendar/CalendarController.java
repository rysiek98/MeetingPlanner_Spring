package com.MeetingPlanner.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping()
    private List<Calendar> findAll(){
        return calendarService.findAll();
    }

    @GetMapping(path = "{id}")
    private Optional<Calendar> findById(@PathVariable("id") long id){
        return calendarService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private Calendar setCalendar(@RequestBody Calendar calendar){
        return calendarService.add(calendar);
    }

    @PutMapping()
    private Calendar updateCalendar(@RequestBody Calendar calendar) {
        return calendarService.updateById(calendar);
    }

    @DeleteMapping(path = "{id}")
    private String delete(@PathVariable("id") long id) {
        return calendarService.deleteById(id);
    }

}
