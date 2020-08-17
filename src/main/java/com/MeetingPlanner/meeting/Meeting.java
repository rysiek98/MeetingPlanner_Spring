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
    private LocalTime meetingBegin;
    @NotNull
    private LocalTime meetingEnd;
    private int meetingDuration;

    protected Meeting(){
    }

    public Meeting(LocalTime meetingBegin, LocalTime meetingEnd){
        this.meetingBegin = meetingBegin;
        this.meetingEnd = meetingEnd;
        countDuration();
    }

    public Meeting(long id, LocalTime meetingBegin, LocalTime meetingEnd){
        this.id = id;
        this.meetingBegin = meetingBegin;
        this.meetingEnd = meetingEnd;
        countDuration();
    }

    public void countDuration(){
        meetingDuration = (int) Duration.between(meetingBegin, meetingEnd).toMinutes();
    }

    public static void countDuration(List<Meeting> meetings){
        meetings.forEach(meeting -> meeting.countDuration());
    }

    @Override
    public int compareTo(Meeting o) {
        return this.getMeetingBegin().compareTo(o.meetingBegin);
    }
}
