package com.MeetingPlanner.calendar;

import com.MeetingPlanner.meeting.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping()
    private List<Calendar> findAll(){
        return calendarService.findAllCalendars();
    }

    @GetMapping(path = "{id}")
    private Calendar findById(@PathVariable("id") long id){
        return calendarService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private Calendar setCalendar(@Valid @NotNull @RequestBody Calendar calendar){
        return calendarService.add(calendar);
    }

    @PutMapping()
    private Calendar updateCalendar(@Valid @NotNull @RequestBody Calendar calendar) {
        return calendarService.updateById(calendar);
    }

    @DeleteMapping(path = "{id}")
    private String delete(@PathVariable("id") long id) {
        return calendarService.deleteById(id);
    }

    //Methods calculate possible meeting time beetwen two calendars.
    @GetMapping(value = "/newMeeting/{duration}/{id1}/{id2}")
    private List<Meeting> planNewMeeting(@PathVariable LocalTime duration, @PathVariable Long id1,@PathVariable Long id2){
        return calendarService.planNewMeeting(duration, id1, id2);
    }

}
