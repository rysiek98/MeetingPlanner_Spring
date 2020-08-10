package com.MeetingPlanner;

import com.MeetingPlanner.calendar.Calendar;
import com.MeetingPlanner.meeting.Meeting;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.sort;

public class MeetingPlannerLogic {

    public static List<Meeting> planNewMeeting(Calendar calendar1, Calendar calendar2, LocalTime duration) {

        List<Meeting> calendar1Meeting = calendar1.getMeetings();
        List<Meeting> calendar2Meeting = calendar2.getMeetings();
        sort(calendar1Meeting);
        sort(calendar2Meeting);

        List<Meeting> possibleMeetingsTime = beforeFirstPlanedMeeting(calendar1, calendar2, calendar1Meeting, calendar2Meeting);
        possibleMeetingsTime.addAll(betweenPlanedMeeting(calendar1, calendar2, calendar1Meeting, calendar2Meeting));
        possibleMeetingsTime.addAll(afterPlanedMeeting(calendar1, calendar2, calendar1Meeting, calendar2Meeting));


        possibleMeetingsTime.removeIf(meeting -> meeting.getDuration() < duration.getMinute());

        return possibleMeetingsTime;
    }

    public static List<Meeting> beforeFirstPlanedMeeting(Calendar calendar1, Calendar calendar2, List<Meeting> calendar1Meeting, List<Meeting> calendar2Meeting){

        List<Meeting> possibleMeetingsTime = new ArrayList<>();

        if(calendar1.getWorkBegin() != calendar1Meeting.get(0).getStartTime() && calendar2.getWorkBegin() != calendar2Meeting.get(0).getStartTime()) {
            if (calendar1.getWorkBegin().isAfter(calendar2.getWorkBegin())) {
                if (calendar1Meeting.get(0).getStartTime().isBefore(calendar2Meeting.get(0).getStartTime())) {
                    possibleMeetingsTime.add(new Meeting(calendar1.getWorkBegin(), calendar1Meeting.get(0).getStartTime()));
                } else
                    possibleMeetingsTime.add(new Meeting( calendar1.getWorkBegin(), calendar2Meeting.get(0).getStartTime()));
            } else {
                if (calendar1Meeting.get(0).getStartTime().isBefore(calendar2Meeting.get(0).getStartTime())) {
                    possibleMeetingsTime.add(new Meeting(calendar2.getWorkBegin(), calendar1Meeting.get(0).getStartTime()));
                } else
                    possibleMeetingsTime.add(new Meeting(calendar2.getWorkBegin(), calendar2Meeting.get(0).getStartTime()));
            }
        }
        return possibleMeetingsTime;
    }

    public static List<Meeting> betweenPlanedMeeting(Calendar calendar1, Calendar calendar2, List<Meeting> calendar1Meeting, List<Meeting> calendar2Meeting){

        List<Meeting> possibleMeetingsTime = new ArrayList<>();

        for(int i=0; i< calendar1Meeting.size()-1; i++){
            LocalTime meetingStart, meetingEnd;
            if(calendar1Meeting.get(i).getEndTime().isAfter(calendar2Meeting.get(i).getEndTime())){
                meetingStart = calendar1Meeting.get(i).getEndTime();
            }else {meetingStart = calendar2Meeting.get(i).getEndTime();}

            if(calendar1Meeting.get(i+1).getStartTime().isBefore(calendar2Meeting.get(i+1).getStartTime())){
                meetingEnd = calendar1Meeting.get(i+1).getStartTime();
            }else {meetingEnd = calendar2Meeting.get(i+1).getStartTime();}

            if(meetingStart.isBefore(meetingEnd)){
                possibleMeetingsTime.add(new Meeting(meetingStart,meetingEnd));
            }

        }
        return possibleMeetingsTime;
    }

    public static List<Meeting> afterPlanedMeeting(Calendar calendar1, Calendar calendar2, List<Meeting> calendar1Meeting, List<Meeting> calendar2Meeting){

        int c1MeetingsSize = calendar1Meeting.size()-1;
        int c2MeetingsSize = calendar2Meeting.size()-1;
        List<Meeting> possibleMeetingsTime = new ArrayList<>();

        if(calendar1.getWorkEnd() != calendar1Meeting.get(c1MeetingsSize).getEndTime()
                && calendar2.getWorkEnd() != calendar2Meeting.get(c2MeetingsSize).getEndTime()) {

            if (calendar1.getWorkEnd().isBefore(calendar2.getWorkEnd())) {
                if (calendar1Meeting.get(c1MeetingsSize).getEndTime().isAfter(calendar2Meeting.get(c2MeetingsSize).getEndTime())) {
                    possibleMeetingsTime.add(new Meeting(calendar1Meeting.get(c1MeetingsSize).getEndTime(), calendar1.getWorkEnd()));
                } else
                    possibleMeetingsTime.add(new Meeting(calendar2Meeting.get(c2MeetingsSize).getEndTime(), calendar1.getWorkEnd()));
            } else {
                if (calendar1Meeting.get(c1MeetingsSize).getEndTime().isAfter(calendar2Meeting.get(c2MeetingsSize).getEndTime())) {
                    possibleMeetingsTime.add(new Meeting(calendar1Meeting.get(c1MeetingsSize).getEndTime(), calendar2.getWorkEnd()));
                } else
                    possibleMeetingsTime.add(new Meeting(calendar2Meeting.get(c2MeetingsSize).getEndTime(),calendar2.getWorkEnd()));
            }
        }
        return possibleMeetingsTime;
    }

}
