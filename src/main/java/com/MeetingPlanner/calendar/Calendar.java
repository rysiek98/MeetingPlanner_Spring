package com.MeetingPlanner.calendar;

import com.MeetingPlanner.meeting.Meeting;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime workBegin;
    private LocalTime workEnd;

    //@OneToMany
    //private List<Meeting> meetings;

}


