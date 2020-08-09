package com.MeetingPlanner.calendar;

import com.MeetingPlanner.meeting.Meeting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime workBegin;
    private LocalTime workEnd;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Meeting> meetings;

    public Long getId() {
        return id;
    }

    public LocalTime getWorkBegin() {
        return workBegin;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setWorkBegin(LocalTime workBegin) {
        this.workBegin = workBegin;
    }

    public void setWorkEnd(LocalTime workEnd) {
        this.workEnd = workEnd;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}


