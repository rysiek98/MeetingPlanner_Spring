package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
public class Meeting {

    @Id
    private Long id;
    private  LocalTime startTime;
    private  LocalTime endTime;
    private int duration;


    public void setDuration(){
        duration = (int) Duration.between(startTime, endTime).toMinutes();
    }

}
