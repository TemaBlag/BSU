CREATE OR REPLACE FUNCTION GET_INFORMATION(
    name_role CHAR(20)
) RETURNS TABLE(
	get_name CHAR(40),
	get_surname CHAR(40),
	w_title CHAR(50), 
	h_placeofresidence CHAR(50),
	j_title CHAR(40),
	get_adress CHAR(40),
	get_email CHAR(40)
)
AS $$
BEGIN
    RETURN QUERY
    SELECT surname, name, w.title, h.placeofresidence, j.title, adress, email
    FROM participants p
    INNER JOIN names n ON p.name_id = n.id
	INNER JOIN roles r ON p.role_id = r.id
	LEFT JOIN workplace w ON p.workplace_id = w.id
	LEFT JOIN housing h ON p.housing_id = h.id
	LEFT JOIN jobtitle j ON p.jobtitle_id = j.id
    WHERE r.title = name_role;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM GET_INFORMATION('lecture');


CREATE OR REPLACE FUNCTION GET_INFORMATION_REPORT(
    num_meet INTEGER
) RETURNS TABLE(
	get_sec_title CHAR(40),
	get_summary TEXT,
	get_published BOOLEAN 
)
AS $$
BEGIN
    RETURN QUERY
    SELECT s.title, summary, published
    FROM report r
    INNER JOIN meeting m ON r.meeting_id = m.id
	LEFT JOIN section s ON r.section_id = s.id
    WHERE m.number = num_meet;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM GET_INFORMATION_REPORT(1);



CREATE OR REPLACE FUNCTION new_email()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE participants 
	SET email = CASE
	WHEN email IS NULL THEN 'unknow@gmail.com'
	ELSE email
	   END;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_participants
AFTER INSERT ON participants
FOR EACH ROW
EXECUTE FUNCTION new_email();
SELECT * FROM public.participants
ORDER BY participant_id ASC

INSERT INTO participants VALUES
(12, 1, 2, 4, 2, 2, NULL, NULL);


CREATE VIEW organizers
AS 
SELECT name, surname, adress, email
FROM participants
INNER JOIN names ON participants.name_id = names.id
INNER JOIN roles ON participants.role_id = roles.id
WHERE roles.title = 'organizer';


SELECT * FROM organizers;


CREATE VIEW reports
AS 
SELECT s.title AS section_title, m.title AS meeting_title, m.number AS meeting_number, summary, published
FROM report r
INNER JOIN section s ON r.section_id = s.id
INNER JOIN meeting m ON r.meeting_id = m.id;


SELECT * FROM reports;