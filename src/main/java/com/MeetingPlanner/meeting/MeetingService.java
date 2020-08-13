package com.MeetingPlanner.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;


    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

        Collections.reverse(meetings);
        meetings.removeIf(meeting -> meetings.size() > updateMeetings.size());
        Collections.reverse(meetings);


        for(int i = 0; i < updateMeetings.size(); i++){
            if(i<meetings.size()) {
                meetings.get(i).setStartTime(updateMeetings.get(i).getStartTime());
                meetings.get(i).setEndTime(updateMeetings.get(i).getEndTime());
            }else {
                meetings.add(updateMeetings.get(i));
            }
            meetings.get(i).countDuration();
        }
    }
}
