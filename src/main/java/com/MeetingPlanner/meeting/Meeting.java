package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Meeting implements Comparable<Meeting> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    private int duration;

    protected Meeting(){
    }

    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        countDuration();
    }

    public Meeting(long id, LocalTime startTime, LocalTime endTime){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        countDuration();
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void countDuration(){
        duration = (int) Duration.between(startTime, endTime).toMinutes();
    }

    public static void countDuration(List<Meeting> meetings){
        for(Meeting meeting:meetings){
            meeting.countDuration();
        }
    }

    @Override
    public int compareTo(Meeting o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
