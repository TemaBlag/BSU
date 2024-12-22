CREATE TABLE participants(
    participant_id INTEGER PRIMARY KEY,
	role_id INTEGER,
	author_id INTEGER,
	workplace_id INTEGER,
	housing_id INTEGER,
	jobTitle_id INTEGER,
	adress CHARACTER(40),
	email CHARACTER(40)
);


CREATE TABLE roles (
	id INTEGER PRIMARY KEY,
	title CHARACTER(20)
);


CREATE TABLE authors (
	id INTEGER PRIMARY KEY,
    name CHARACTER(30),
	surname CHARACTER(30)
);


CREATE TABLE workplace (
	id INTEGER PRIMARY KEY,
	title CHARACTER(50)
);


CREATE TABLE jobTitle (
	id INTEGER PRIMARY KEY,
	title CHARACTER(40)
);


CREATE TABLE housing (
	id INTEGER PRIMARY KEY,
	placeOfResidence CHARACTER(50)
);


CREATE TABLE report (
	report_id INTEGER PRIMARY KEY,
	section_id INTEGER,
	meeting_id INTEGER,
	summary TEXT,
	published BOOL
);


CREATE TABLE meeting (
	id INTEGER PRIMARY KEY,
    title CHARACTER(40),
	number INTEGER
);


CREATE TABLE section (
	id INTEGER PRIMARY KEY,
	title CHARACTER(40)
);


ALTER TABLE participants
ADD CONSTRAINT fk_role
FOREIGN KEY (role_id) REFERENCES roles (id);


ALTER TABLE participants
ADD CONSTRAINT fk_author
FOREIGN KEY (author_id) REFERENCES authors (id);


ALTER TABLE participants
ADD CONSTRAINT fk_workplace
FOREIGN KEY (workplace_id) REFERENCES workplace (id);


ALTER TABLE participants
ADD CONSTRAINT fk_jobTitle
FOREIGN KEY (jobTitle_id) REFERENCES jobTitle (id);


ALTER TABLE participants
ADD CONSTRAINT fk_housing
FOREIGN KEY (housing_id) REFERENCES housing (id);

ALTER TABLE report
ADD CONSTRAINT fk_section
FOREIGN KEY (section_id) REFERENCES section (id);


ALTER TABLE report
ADD CONSTRAINT fk_meeting
FOREIGN KEY (meeting_id) REFERENCES meeting (id);

ALTER TABLE participants
ADD CONSTRAINT unique_role_id
UNIQUE (role_id);


CREATE TABLE report_participant (
    report_id INTEGER REFERENCES report (report_id),
    role_id INTEGER REFERENCES participants (role_id),
	participant_id INTEGER REFERENCES participants (participant_id),
    PRIMARY KEY (report_id, role_id, participant_id)
);





