package com.MeetingPlanner.workinghours;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class WorkingHours {

    private  LocalTime workingBegin;
    private  LocalTime workingEnd;
    private UUID id;

    public WorkingHours(LocalTime workingBegin
            , LocalTime workingEnd) {
        this.workingBegin = workingBegin;
        this.workingEnd = workingEnd;
    }

    public WorkingHours() {

    }

    public LocalTime getWorkingBegin() {
        return workingBegin;
    }

    public LocalTime getWorkingEnd() {
        return workingEnd;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public void setWorkingBegin(LocalTime workingBegin) {
        this.workingBegin = workingBegin;
    }

    public void setWorkingEnd(LocalTime workingEnd) {
        this.workingEnd = workingEnd;
    }
}
