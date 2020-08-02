CREATE TABLE Calendar (
    calendar_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_old VARCHAR(40) NOT NUll,
    data DATE NOT NULL,
    workBegin TIME NOT NULL,
    workEnd TIME NOT NULL
);

CREATE TABLE Meeting(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meeting_id BIGINT NOT NULL,
    startTime TIME Not NULL,
    endTime TIME NOT NULL
);

ALTER TABLE Meeting
    ADD CONSTRAINT  meeting_calendar_id
     FOREIGN KEY(meeting_id) REFERENCES Calendar(calendar_id)