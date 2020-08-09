package com.MeetingPlanner.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int duration;

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

    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

        for(int i = 0; i<updateMeetings.size(); i++){
            meetings.get(i).setStartTime(updateMeetings.get(i).getStartTime());
            meetings.get(i).setEndTime(updateMeetings.get(i).getEndTime());
            meetings.get(i).countDuration();
        }

    }

}
