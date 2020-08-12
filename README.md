# MeetingPlanner_Spring

Application created in Java with Spring. MeetingPlanner use H2 in-memory database to store Calendars which user adds. Each calendar has: id (auto incremented by Hibernate), field date, fields: workingBegnin and workingEnd to store working hours and fileds to store planed meetings time. When we add to database some calendars application might calcoulate new meeting time, to do that it needs two calendars (chosen calendars by id from database) and duration of new meeting.

For example:

Calendar 1:
     
    {
        
        "data": "2020-08-03",
        "workBegin": "09:00:00",
        "workEnd": "20:00",
        "meetings": [
            {
                "startTime": "09:00",
                "endTime": "10:30"
            },
            {
                "startTime": "12:00",
                "endTime": "13:00"
            },
            {
                "startTime": "16:00",
                "endTime": "18:30"
            }

        ]
    }
    
    
Calendar 2:
    
    {
        
        "data": "2020-08-03",
        "workBegin": "10:00:00",
        "workEnd": "18:30",
        "meetings": [
            {
                "startTime": "10:00",
                "endTime": "11:30"
            },
            {
                "startTime": "12:30",
                "endTime": "14:30"
            }

        ]
    }

Duration of new meeting: 00:30

Output:

    [
            {
                "id":1,
                "startTime": "11:30:00",
                "endTime": "12:00:00",
                "duration":30
            },
            {
                "id":2,
                "startTime": "14:30:00",
                "endTime": "16:00:00"
                "duration": 90
            }

    ]

When you run application, you can use e.g. Postman to send HTTP request to communicate with application, add date, delete, modify and calculate new meetings.  
Paths for Post, Put: localhost:8080/api/calendar
Paths for Get by id, and Delete: localhost:8080/api/calendar/"chosen id"
Path to calculate new meeting, use Get and path localhost:8080/api/calendar/newMeeting/"duration"/"id1"/"id2"


Author: Michał Ryszka 11/08/20
