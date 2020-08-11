package com.MeetingPlanner.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;


    public static void updateMeetings(List<Meeting> meetings,List<Meeting> updateMeetings) {

        if(updateMeetings.size() < meetings.size()) {
            for (int i = meetings.size()-1; i > updateMeetings.size()-1; i--) {
                meetings.remove(meetings.get(i));
            }
        }
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
