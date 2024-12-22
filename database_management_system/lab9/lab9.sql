INSERT INTO "STUDENTS_2" VALUES
	(3417, 'Пипко', 'Артём', 'Олегович', 0),
	(3418, 'Лещик', 'Дмитрий', 'Анатольевич', 0),
	(3419, 'Коновалова', 'Алина', 'Сергеевна', 0),
	(3420, 'Богук', 'Дарья', 'Андреевна', 0),
	(3421, 'Коновалова', 'Алина', 'Сергеевна', 0),
	(3422, 'Благодарный', 'Артём', 'Андреевич', 0),
	(3423, 'Козловский', 'Антон', 'Ивановаич', 0),
	(3424, 'Трофимович', 'Тимофей', 'Андреевич', 0),
	(3425, 'Тюкалов', 'Александр', 'Сергеевич', 0),
	(3426, 'Щербо', 'Никита', 'Сергеевич', 0);


SELECT conname AS constraint_name, contype AS constraint_type
FROM pg_constraint
WHERE conrelid = 'PREDMET_2'::regclass;



ALTER TABLE "PREDMET_2"
DROP CONSTRAINT "check_name";


INSERT INTO "PREDMET_2" VALUES
	(2006, 'Базы данных', NULL, 34),
	(2007, 'Языки программирования', NULL, 68),
	(2008, 'Проектирование информационных систем', NULL, 17);
	

UPDATE "PREDMET_2"
SET "TNUM" = CASE
    WHEN "PNAME" = 'Базы данных' THEN
	    (SELECT "TNUM" FROM "PREDMET_2" WHERE "PNAME" = 'Физика')
    WHEN "PNAME" = 'Языки программирования' THEN
	    (SELECT "TNUM" FROM "PREDMET_2" WHERE "PNAME" = 'Химия')
    WHEN "PNAME" = 'Проектирование информационных систем' THEN
	     (SELECT "TNUM" FROM "PREDMET_2" WHERE "PNAME" = 'Философия')
	ELSE
	    "TNUM"
    END;	


INSERT INTO "USP_2"
SELECT 1005 + ROW_NUMBER() OVER (ORDER BY "SNUM"),
       FLOOR(RANDOM() * 5) + 1,
	   CURRENT_DATE,
	   "SNUM",
	   "PNUM"
FROM "STUDENTS_2" CROSS JOIN "PREDMET_2"
WHERE "STIP" = CAST(0.00 AS MONEY) AND "COURS" IS NULL;


UPDATE "STUDENTS_2"
SET "STIP" = 30
WHERE "SNUM" IN 
   (SELECT "SNUM"
    FROM "USP_2" 
    GROUP BY "SNUM"
    HAVING COUNT(CASE WHEN "OCENKA" = 5 THEN 1 END) >= 2);


UPDATE "STUDENTS_2"
SET "STIP" = CASE
     WHEN "STIP" <> CAST(0 AS MONEY) THEN CAST(5 AS MONEY)
	 ELSE "STIP"
	 END
WHERE "SNUM" IN 
   (SELECT "SNUM"
    FROM "USP_2" 
    GROUP BY "SNUM"
    HAVING COUNT(CASE WHEN "OCENKA" = 3 THEN 1 END) > 0);
	
SELECT *
FROM "STUDENTS_2"

DELETE FROM "STUDENTS_2"
WHERE "SNUM" IN 
   (SELECT "SNUM"
    FROM "USP_2" 
    GROUP BY "SNUM"
    HAVING COUNT(CASE WHEN "OCENKA" = 2 THEN 1 END) > 2);
	
SELECT *
FROM "STUDENTS_2"












