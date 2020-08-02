package com.MeetingPlanner.calendar;

import com.MeetingPlanner.meeting.Meeting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime workBegin;
    private LocalTime workEnd;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Meeting> meetings;

}


