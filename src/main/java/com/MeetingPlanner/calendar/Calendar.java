package com.MeetingPlanner.calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Calendar {
    @Id
    private long calendar_id;
    private LocalDate data;
    private LocalTime workBegin;
    private LocalTime workEnd;

}


