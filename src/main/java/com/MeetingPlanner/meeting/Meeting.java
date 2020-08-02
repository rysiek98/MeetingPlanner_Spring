package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Meeting {

    private  LocalTime startTime;
    private  LocalTime endTime;
    private int duration;
    private Long id;

    public Meeting(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Meeting() {

    }

    public void setDuration(){
        duration = (int) Duration.between(startTime, endTime).toMinutes();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
