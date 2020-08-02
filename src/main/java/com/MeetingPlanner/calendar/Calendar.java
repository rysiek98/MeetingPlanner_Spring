package com.MeetingPlanner.calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Calendar {

    private  UUID id;
    private  LocalDate date;

    public Calendar(LocalDate date) {
        this.date = date;
    }

    public Calendar() {

    }

    @Id
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}


