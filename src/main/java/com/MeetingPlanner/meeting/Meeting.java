package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Meeting {

    @Id
    private Long id;
    private  LocalTime startTime;
    private  LocalTime endTime;

}
