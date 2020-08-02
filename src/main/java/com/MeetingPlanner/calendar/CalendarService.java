package com.MeetingPlanner.calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    Optional<Calendar> findById(long id) {
     return calendarRepository.findById(id);
    }

    String deleteById(long id) {
     if(calendarRepository.existsById(id)) {
         calendarRepository.deleteById(id);
         return "Delete";
     }
     return "Not found";
    }

    Calendar updateById(long id, Calendar calendar) {
     return calendarRepository.getOne(id);
    }
}
