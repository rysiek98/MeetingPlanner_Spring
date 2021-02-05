# MeetingPlanner_Spring

Application created in Java with Spring. MeetingPlanner use H2 in-memory database to store Calendars which user adds. Each calendar has: id (auto incremented by Hibernate), field date, fields: workingBegnin and workingEnd to store working hours and fileds to store planed meetings time. When we add to database some calendars application might calculate new meeting time, to do that it needs two calendars (chosen calendars by id from database) and duration of new meeting.

For example:

Calendar 1:
     
    {
        
        "date": "2020-08-03",
        "workBegin": "09:00:00",
        "workEnd": "20:00",
        "meetings": [
            {
                "meetingBegin": "09:00",
                "meetingEnd": "10:30"
            },
            {
                "meetingBegin": "12:00",
                "meetingEnd": "13:00"
            },
            {
                "meetingBegin": "16:00",
                "meetingEnd": "18:30"
            }

        ]
    }
    
    
Calendar 2:
    
    {
        
        "date": "2020-08-03",
        "workBegin": "10:00:00",
        "workEnd": "18:30",
        "meetings": [
            {
                "meetingBegin": "10:00",
                "meetingEnd": "11:30"
            },
            {
                "meetingBegin": "12:30",
                "meetingEnd": "14:30"
            }

        ]
    }

Duration of new meeting: 00:30

Output:

    [
            {
                "id":1,
                "meetingBegin": "11:30:00",
                "meetingEnd": "12:00:00",
                "meetingDuration":30
            },
            {
                "id":2,
                "meetingBegin": "14:30:00",
                "meetingEnd": "16:00:00"
                "meetingDuration": 90
            }

    ]

When you run application, you can use e.g. Postman to send HTTP request to communicate with application, add date, delete, modify and calculate new meetings.  
Paths for Post, Put: localhost:8080/api/calendar
Paths for Get by id, and Delete: localhost:8080/api/calendar/"chosen id"
Path to calculate new meeting, use Get and path localhost:8080/api/calendar/newMeeting/"duration"/"id1"/"id2"


Created by: Michał Ryszka, 2020
