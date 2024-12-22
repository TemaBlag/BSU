INSERT INTO names VALUES
	(1, 'Sobolevskaya', 'Elena'),
	(2, 'Alexander', 'Tolstikov'),
	(3, 'Kotov', 'Vladimir'),
	(4, 'Vaskovsky', 'Maksim'),
	(5, 'Razmyslovich', 'Gregory'),
	(6, 'Bezmen', 'Nikolay'),
	(7, 'Blagodarniy', 'Artyom'),
	(8, 'Volkov', 'Pavel'),
	(9, 'Vorobyova', 'Ulyana'),
	(10, 'Obykhovich', 'Ulyana');
	
INSERT INTO roles VALUES
	(1, 'lecture'),
	(2, 'listener'),
	(3, 'organizer'),
	(4, 'security'),
	(5, 'guest');
	
	
INSERT INTO workplace VALUES
	(1, 'BSU'),
	(2, 'BSUIR'),
	(3, 'BSTU'),
	(4, 'Yandex'),
	(5, 'IsSoft');
	
	
INSERT INTO housing VALUES
	(1, 'hotel "Belarus"'),
	(2, 'hostel 6'),
	(3, 'hostel 2'),
	(4, 'hotel "View"'),
	(5, 'BonHotel');
	
	
INSERT INTO jobTitle VALUES
	(1, 'lecter at university'),
	(2, 'software developer'),
	(3, 'data scientist'),
	(4, 'school teacher'),
	(5, 'laboratory assistant'),
	(6, 'student');
		
	
INSERT INTO section VALUES
	(1, 'Introduction'),
	(2, 'University aducation'),
	(3, 'News in IT'),
	(4, 'Vacancy'),
	(5, 'Conclusion');
	
	
INSERT INTO meeting VALUES
	(1, 'summer internships', 1),
	(2, 'how to enter IT', 2),
	(3, 'internships in Yandex', 3),
	(4, 'internships in Tinkoff', 4),
	(5, 'internships in EPAM', 5);	
	

INSERT INTO participants VALUES
	(1, 1, 1, 1, 1, 1, 'Independence st.,42','sobel@gmail.com'),
	(2, 1, 2, 1, 4, 1, 'Independence st.,15','altolst@gmail.com'),
	(3, 1, 3, 1, 5, 1, 'Independence st.,23','kot@gmail.com'),
	(4, 4, NULL, 1, 5, NULL, NULL, 'sec1@gmail.com'),
	(5, 4, 5, 2, 4, NULL, NULL, 'sec2@gmail.com'),
	(6, 4, 4, 3, 4, NULL, NULL, 'sec3@gmail.com'),
	(7, 2, 7, 1, NULL, 6, NULL, 'part1@gmail.com'),
	(8, 2, 6, 2, NULL, 6, NULL, 'part2@gmail.com'),
	(9, 4, 8, 3, NULL, 6, NULL, 'part3@gmail.com'),
	(10, 3, 9, 4, 2, 3, 'Oktyabrskay st., 10', 'org1@gmail.com'),
	(11, 3, 10, 5, 3, 2, 'Oktyabrskay st., 2', 'org2@gmail.com');
	

	
	
INSERT INTO report VALUES
	(1, 1, 1, 'introduction to anyone', false),
	(2, 2, 1, 'Yandex', true),
	(3, 3, 1, 'Tinkoff', true),
	(4, 4, 1, 'Epam', true),
	(5, 5, 1, 'summary', false);
	

INSERT INTO report_participant VALUES
	(1, 9),
	(2, 2),
	(3, 3),
	(4, 1),
	(5, 10);	
	
	
	
	
	
	
	
	