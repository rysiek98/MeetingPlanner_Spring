package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Meeting {

    private  LocalTime startTime;
    private  LocalTime endTime;
    private int duration;
    @Id
    private long id;
    private long Meeting_id;

    public void setDuration(){
        duration = (int) Duration.between(startTime, endTime).toMinutes();
    }

}
