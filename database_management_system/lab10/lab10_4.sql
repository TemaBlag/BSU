SELECT COUNT(*) AS количество_участников
FROM participants;
	
	
SELECT name, surname AS фио_лекторов
FROM participants
INNER JOIN names ON participants.name_id = names.id
WHERE role_id = 1;
	
	
SELECT COUNT(*) AS количество_слушателей
FROM participants
WHERE role_id = 2;	
	
SELECT title AS план_мероприятия
FROM section;
	
	
SELECT name AS имя, surname AS фамилия
FROM participants
INNER JOIN names ON participants.name_id = names.id
INNER JOIN housing ON participants.housing_id = housing.id
WHERE placeofresidence = 'hostel 2';


SELECT summary AS публикации
FROM report
WHERE published = true;



SELECT title AS должность, name AS имя, surname AS фамилия, summary AS выступление
FROM participants
INNER JOIN jobtitle ON participants.jobtitle_id = jobtitle.id
INNER JOIN names ON participants.name_id = names.id
INNER JOIN report_participant USING(participant_id)
INNER JOIN report USING(report_id)
WHERE jobtitle.title = 'software developer';


SELECT name, surname, adress, email
FROM participants
INNER JOIN names ON participants.name_id = names.id
INNER JOIN roles ON participants.role_id = roles.id
WHERE roles.title = 'organizer';


SELECT name AS имя, surname AS фамилия, housing.placeofresidence AS место
FROM participants
INNER JOIN housing ON participants.housing_id = housing.id
INNER JOIN names ON participants.name_id = names.id
INNER JOIN roles ON participants.role_id = roles.id
WHERE roles.title = 'security';


SELECT meeting.title AS встреча, section.title AS тема
FROM report
INNER JOIN meeting ON report.meeting_id = meeting.id
INNER JOIN section ON report.section_id = section.id
WHERE meeting.number = 1;







