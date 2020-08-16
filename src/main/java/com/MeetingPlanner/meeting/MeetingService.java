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

    public static void removeMeetings(List<Meeting> meetings,List<Meeting> updateMeetings){
        Collections.reverse(meetings);
        meetings.removeIf(meeting -> meetings.size() > updateMeetings.size());
        Collections.reverse(meetings);
    }

    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

        removeMeetings(meetings, updateMeetings);

        updateMeetings.stream().forEach(meeting -> {
            if (updateMeetings.indexOf(meeting) < meetings.size()) {
                meetings.get(updateMeetings.indexOf(meeting)).setStartTime(meeting.getStartTime());
                meetings.get(updateMeetings.indexOf(meeting)).setEndTime(meeting.getEndTime());
            } else {
                meetings.add(meeting);
            }
            meetings.get(updateMeetings.indexOf(meeting)).countDuration();
        });

    }

    public static boolean newMeetingCalculateCondition(Meeting meeting
            ,Meeting meetingCalendar, int meetingDuration){

        LocalTime newMeetingEndTime = meeting.getEndTime().minusMinutes(meetingDuration);

        if(newMeetingEndTime.isAfter(meetingCalendar.getStartTime())
                || newMeetingEndTime.equals(meetingCalendar.getStartTime())){
            return true;
        }
        else return false;
    }

    public static List<Meeting> newMeetingCalculate(Calendar calendar1, Calendar calendar2, int meetingDuration){

        List<Meeting> possibleMeetingTime = new ArrayList<>();
        int iterator = 1;

        List<Meeting> calendar1Meetings = countFreeTime(calendar1).stream()
                .filter(meeting -> meeting.getDuration() >= meetingDuration).collect(Collectors.toList());

        List<Meeting> calendar2Meetings = countFreeTime(calendar2).stream()
                .filter(meeting -> meeting.getDuration() >= meetingDuration).collect(Collectors.toList());


        for(Meeting meetingCalendar1 : calendar1Meetings) {
            for (Meeting meetingCalendar2 : calendar2Meetings) {

                if(meetingCalendar1.getStartTime().isBefore(meetingCalendar2.getStartTime())
                        || meetingCalendar1.getStartTime().equals(meetingCalendar2.getStartTime())){

                    if(newMeetingCalculateCondition(meetingCalendar1
                            ,meetingCalendar2 ,meetingDuration)){

                        possibleMeetingTime.add(new Meeting(
                                iterator
                                ,meetingCalendar2.getStartTime()
                                ,meetingCalendar1.getEndTime()));
                        iterator++;
                    }
                }else {
                    if(newMeetingCalculateCondition(meetingCalendar2
                            ,meetingCalendar1 ,meetingDuration)){

                        possibleMeetingTime.add(new Meeting(
                                iterator
                                ,meetingCalendar1.getStartTime()
                                ,meetingCalendar2.getEndTime()));
                        iterator++;
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
