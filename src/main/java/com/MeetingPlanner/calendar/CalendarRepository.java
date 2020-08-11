package com.MeetingPlanner.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CalendarRepository extends JpaRepository <Calendar, Long> {

    @Query("select distinct c from Calendar c left join fetch c.meetings")
    List<Calendar> findAllCalendars();

}
