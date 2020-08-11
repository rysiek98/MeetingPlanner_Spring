package com.MeetingPlanner;

import com.MeetingPlanner.meeting.Meeting;
import com.MeetingPlanner.calendar.Calendar;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.sort;

public class MeetingPlannerLogic {

    public static List<Meeting> newMeetingCalculate(Calendar calendar1, Calendar calendar2, int meetingDuration){

        List<Meeting> possibleMeetingTime = new ArrayList<>();
        List<Meeting> calendar1Meetings = countFreeTime(calendar1);
        List<Meeting> calendar2Meetings = countFreeTime(calendar2);
        LocalTime startTime, endTime;
        int iterator = 1;

        for (Meeting meeting : calendar1Meetings) {
            for (Meeting value : calendar2Meetings) {
                if (meeting.getStartTime().isAfter(value.getStartTime()) || meeting.getStartTime().equals(value.getStartTime())) {
                    startTime = meeting.getStartTime();
                    endTime = meeting.getStartTime().plusMinutes(meetingDuration);

                    boolean flag = false;
                    while (condition1(meeting, value, endTime)) {
                        endTime = endTime.plusMinutes(meetingDuration);
                        flag = true;
                    }
                    if (flag) {
                        possibleMeetingTime.add(new Meeting(iterator,startTime, endTime.minusMinutes(meetingDuration)));
                        iterator++;
                    }

                } else {
                    startTime = value.getStartTime();
                    endTime = value.getStartTime().plusMinutes(meetingDuration);
                    boolean flag = false;
                    while (condition2(meeting, value, endTime)) {
                        endTime = endTime.plusMinutes(meetingDuration);
                        flag = true;
                    }
                    if (flag) {
                        possibleMeetingTime.add(new Meeting(iterator,startTime, endTime.minusMinutes(meetingDuration)));
                        iterator++;
                    }
                }
            }
        }
        return possibleMeetingTime;
    }

    public static boolean condition1(Meeting meeting, Meeting value, LocalTime endTime){
        return (endTime.isBefore(meeting.getEndTime()) && endTime.isBefore(value.getEndTime()) && endTime.isAfter(value.getStartTime()))
                || (endTime.equals(meeting.getEndTime()) && endTime.isBefore(value.getEndTime()) && endTime.isAfter(value.getStartTime()))
                || (endTime.isBefore(meeting.getEndTime()) && endTime.equals(value.getEndTime()) && endTime.isAfter(value.getStartTime()))
                || (endTime.equals(meeting.getEndTime()) && endTime.equals(value.getEndTime()) && endTime.isAfter(value.getStartTime()));
    }

    public static boolean condition2(Meeting meeting, Meeting value, LocalTime endTime){
        return (endTime.isBefore(meeting.getEndTime()) && endTime.isBefore(value.getEndTime()) && endTime.isAfter(meeting.getStartTime()))
                || (endTime.equals(meeting.getEndTime()) && endTime.isBefore(value.getEndTime()) && endTime.isAfter(meeting.getStartTime()))
                || (endTime.isBefore(meeting.getEndTime()) && endTime.equals(value.getEndTime()) && endTime.isAfter(meeting.getStartTime()))
                || (endTime.equals(meeting.getEndTime()) && endTime.equals(value.getEndTime()) && endTime.isAfter(meeting.getStartTime()));
    }

    public static List<Meeting> countFreeTime(Calendar calendar){

        List<Meeting> meetings = calendar.getMeetings();
        List<Meeting> freeTime = new ArrayList<>();

        sort(meetings);
        if(meetings.size() == 0){
            freeTime.add(new Meeting(calendar.getWorkBegin(), calendar.getWorkEnd()));
        }else{

            if(!calendar.getWorkBegin().equals(meetings.get(0).getStartTime())){
                freeTime.add(new Meeting(calendar.getWorkBegin(), meetings.get(0).getStartTime()));
            }

            for(int i=0; i<meetings.size()-1; i++){
                if(!meetings.get(i).getEndTime().equals(meetings.get(i+1).getStartTime())){
                    freeTime.add(new Meeting(meetings.get(i).getEndTime(), meetings.get(i+1).getStartTime()));
                }
            }

            if(!calendar.getWorkEnd().equals(meetings.get(meetings.size()-1).getEndTime())){
                freeTime.add(new Meeting((meetings.get(meetings.size()-1).getEndTime()), calendar.getWorkEnd()));
            }
        }
        return freeTime;
    }

    public static List<Meeting> newMeetingTime(Calendar calendar1, Calendar calendar2, int meetingDuration){
        if(calendar1.getData().equals(calendar2.getData())){
            return newMeetingCalculate(calendar1,calendar2,meetingDuration);
        }else return new ArrayList<>();
    }
}
