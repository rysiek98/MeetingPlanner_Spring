package com.MeetingPlanner.calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

     List<Calendar> findAll(){
        return calendarRepository.findAll();
    }

     Calendar add(Calendar calendar){
        return calendarRepository.save(calendar);
    }
}
