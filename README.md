# MeetingPlanner_Spring

Application created in Java with Spring. MeetingPlanner use H2 inmemory database to store Calendars which users adds. Each calendar has: id (auto incremented by Hibernate), field data, fields: workingBegnin and workingEnd to store working hours and fileds to store planed meetings time. When we add to database some calendars application might calcoulate new meeting time, to do that it needs two calendars (chosen calendars by id) and duration of new meeting.

For example

Calendar 1                                  
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
    
    Calendar 2
    {
        "id":1,
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
        "id": 1,
        "startTime": "11:30:00",
        "endTime": "12:00:00",
        "duration": 30
    },
    {
        "id": 2,
        "startTime": "14:30:00",
        "endTime": "16:00:00",
        "duration": 90
    }
]

