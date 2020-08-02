package com.MeetingPlanner.calendar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Calendar {

    private Long id;
    private LocalDate date;
    private LocalTime workingBegin;
    private LocalTime workingEnd;

    public Calendar(LocalDate date) {
        this.date = date;
    }

    public Calendar() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}


