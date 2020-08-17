package com.MeetingPlanner.meeting;

import com.MeetingPlanner.calendar.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Collections.sort;


@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public static void removeRedundantMeetings(List<Meeting> meetings, List<Meeting> updateMeetings){
        Collections.reverse(meetings);
        meetings.removeIf(meeting -> meetings.size() > updateMeetings.size());
        Collections.reverse(meetings);
    }


    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

        removeRedundantMeetings(meetings, updateMeetings);

        updateMeetings.forEach(meeting -> {
            if (updateMeetings.indexOf(meeting) < meetings.size()) {
                meetings.get(updateMeetings.indexOf(meeting)).setMeetingBegin(meeting.getMeetingBegin());
                meetings.get(updateMeetings.indexOf(meeting)).setMeetingEnd(meeting.getMeetingEnd());
            } else {
                meetings.add(meeting);
            }
            meetings.get(updateMeetings.indexOf(meeting)).countDuration();
        });

    }


    public static boolean newMeetingCalculateCondition(Meeting meeting
            ,Meeting meetingCalendar, int meetingDuration){

        LocalTime newMeetingEndTime = meeting.getMeetingEnd().minusMinutes(meetingDuration);

        return newMeetingEndTime.isAfter(meetingCalendar.getMeetingBegin())
                || newMeetingEndTime.equals(meetingCalendar.getMeetingBegin());
    }


    public static List<Meeting> matchMeetingsByDuration(Calendar calendar, int meetingDuration){
        return countFreeTime(calendar).stream()
                .filter(meeting -> meeting.getMeetingDuration() >= meetingDuration).collect(Collectors.toList());
    }


    public static List<Meeting> newMeetingCalculate(Calendar calendar1, Calendar calendar2, int meetingDuration){

        List<Meeting> possibleMeetingTime = new ArrayList<>();
        int index = 1;

        List<Meeting> calendar1Meetings = matchMeetingsByDuration(calendar1, meetingDuration);
        List<Meeting> calendar2Meetings = matchMeetingsByDuration(calendar2, meetingDuration);


        for(Meeting meetingCalendar1 : calendar1Meetings) {
            for (Meeting meetingCalendar2 : calendar2Meetings) {

                if(meetingCalendar1.getMeetingBegin().isBefore(meetingCalendar2.getMeetingBegin())
                        || meetingCalendar1.getMeetingBegin().equals(meetingCalendar2.getMeetingBegin())){

                    if(newMeetingCalculateCondition(meetingCalendar1
                            ,meetingCalendar2 ,meetingDuration)){

                        possibleMeetingTime.add(new Meeting(
                                index
                                ,meetingCalendar2.getMeetingBegin()
                                ,meetingCalendar1.getMeetingEnd()));
                        index++;
                    }
                }else {
                    if(newMeetingCalculateCondition(meetingCalendar2
                            ,meetingCalendar1 ,meetingDuration)){

                        possibleMeetingTime.add(new Meeting(
                                index
                                ,meetingCalendar1.getMeetingBegin()
                                ,meetingCalendar2.getMeetingEnd()));
                        index++;
                    }
                }
            }
        }

        return possibleMeetingTime;
    }

    public static List<Meeting> countFreeTime(Calendar calendar){

        List<Meeting> meetings = calendar.getMeetings();
        List<Meeting> freeTime = new ArrayList<>();

        sort(meetings);

        if(meetings.isEmpty()){
            freeTime.add(new Meeting(calendar.getWorkBegin(), calendar.getWorkEnd()));
        }else{
            if(!calendar.getWorkBegin().equals(meetings.get(0).getMeetingBegin())){
                freeTime.add(new Meeting(calendar.getWorkBegin(), meetings.get(0).getMeetingBegin()));
            }

            for(int i=0; i<meetings.size()-1; i++){
                if(!meetings.get(i).getMeetingEnd().equals(meetings.get(i+1).getMeetingBegin())){
                    freeTime.add(new Meeting(meetings.get(i).getMeetingEnd(), meetings.get(i+1).getMeetingBegin()));
                }
            }

            if(!calendar.getWorkEnd().equals(meetings.get(meetings.size()-1).getMeetingEnd())){
                freeTime.add(new Meeting((meetings.get(meetings.size()-1).getMeetingEnd()), calendar.getWorkEnd()));
            }
        }
        return freeTime;
    }

    public static List<Meeting> newMeetingTime(Calendar calendar1, Calendar calendar2, int meetingDuration){

        if(calendar1.getDate().equals(calendar2.getDate())){
            return newMeetingCalculate(calendar1,calendar2,meetingDuration);
        }else return new ArrayList<>();
    }

}
