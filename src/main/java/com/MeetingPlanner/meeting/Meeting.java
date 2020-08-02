package com.MeetingPlanner.meeting;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
public class Meeting {

    @Id
    private Long id;
    private  LocalTime startTime;
    private  LocalTime endTime;

}
