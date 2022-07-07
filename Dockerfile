FROM openjdk:11
EXPOSE 8080
ADD build/libs/MeetingPlanner.jar MeetingPlanner.jar
ENTRYPOINT ["java","-jar","/MeetingPlanner.jar"]
