use mydb;
INSERT INTO `mydb`.`SCHOOL` (`NAME`, `ADDRESS`, `PHONE_NUMBER`, `EMAIL`, `CREATION_DATE`) 
      VALUES ('ACA', '3 Hakob Hakobyan, Yerevan, Armenia', '(012) 48-16-32', 'info@aca.am', UNIX_TIMESTAMP(NOW())),
			 ('ISTC', '1/7 Alek Manukian St, Yerevan, Armenia', '(012) 21-97-00', '', UNIX_TIMESTAMP(NOW())),
			 ('Basic IT Center', '18 Isahakyan St, Yerevan, Armenia', '(044) 65-99-88', 'basic@it-center.am', UNIX_TIMESTAMP(NOW()));
 
INSERT INTO `mydb`.`USER` (`FIRSTNAME`, `LASTNAME`, `EMAIL`, `PASSWORD`, `STATUS`, `TYPE`, `CREATION_DATE`) 
      VALUES ('Paruyr', 'Hovakimyan', 'paruyrhovakimyan@gmail.com', 'paruyr.password',  1, 2, UNIX_TIMESTAMP(NOW())),
             ('Narine', 'Gexamyan', 'narinegexamyan@gmail.com', 'narine.password',  1, 2, UNIX_TIMESTAMP(NOW())),
             ('Ashot', 'Virabyan', 'ashotvirabyan@gmail.com', 'ashot.password',  1, 2, UNIX_TIMESTAMP(NOW())),
             ('Anna', 'Xukasyan', 'annaxukasyan@gmail.com', 'anna.password',  1, 2, UNIX_TIMESTAMP(NOW())),
             ('Ara', 'Melqonyan', 'aramelqonyan@gmail.com', 'ara.password',  1, 2, UNIX_TIMESTAMP(NOW())),
              
             ('Gor', 'Hakobyan', 'gorhakobiann@gmail.com', 'gor-2000.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Narek', 'Malkhasyan', 'narekmalkhasyan@gmail.com', 'narek.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Gevorg', 'Ghazaryan', 'gevorgghazaryan@gmail.com', 'gevorg.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Tigranuhi', 'Mkrtchyan', 'tigranuhimkrtchyan@gmail.com', 'tigranuhi.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Vazken', 'Abdulyan', 'vazkhenabdulyan@gmail.com', 'vazkhen.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Armen', 'Gexamyan', 'armengexamyan@gmail.com', 'armen.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Anna', 'Harutyunyan', 'annaharutyunyan@gmail.com', 'anna.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Tigran', 'Qeshishyan', 'tigranqeshishyan@gmail.com', 'tigran.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Albert', 'Avagyan', 'albertavagyan@gmail.com', 'albert.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Narine', 'Minasyan', 'narineminasyan@gmail.com', 'narine.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Vergine', 'Hakobyan', 'verginehakobyan@gmail.com', 'vergine.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Karine', 'Baxramyan', 'karinebaxramyan@gmail.com', 'karine.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Armen', 'Jamkochyan', 'armenjamkochyan@gmail.com', 'armen.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Gayane', 'Tovmasyan', 'gayanetovmasyan@gmail.com', 'gayane.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Anjelika', 'Arzumanyan', 'anjelikaarzumanyan@gmail.com', 'anjelika.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Lilit', 'Gexamyan', 'lilitgexamyan@gmail.com', 'lilit.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Mxitar', 'Ohanyan', 'mxitarohanyan@gmail.com', 'mxitar.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Mher', 'Ohanyan', 'mherohanyan@gmail.com', 'mher.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Hayk', 'Sardaryan', 'hayksardaryan@gmail.com', 'hayk.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Davit', 'Adamyan', 'davitadamyan@gmail.com', 'davit.password', 1, 1, UNIX_TIMESTAMP(NOW())),
             ('Samvel', 'Meliqyan', 'samvelmeliqyan@gmail.com', 'samvel.password', 1, 1, UNIX_TIMESTAMP(NOW()));
             
INSERT INTO `mydb`.`USER_INFO`(`USER_ID`, `PASSPORT_ID`, `SOCIAL_CARD_ID`, `BIRTH_DATE`, `PHONE_NUMBER`, `ADDRESS`, `GENDER`, `CREATION_DATE`)
	  VALUES(1, 'AA012345', '4568913251', 504907200000, '(043) 12-34-56', '5 Kievyan, Yerevan', 1, UNIX_TIMESTAMP(NOW())), 
            (6, 'AA012346', '4568913252', 852062400000, '(043) 12-34-57', '6 Kievyan, Yerevan', 1, UNIX_TIMESTAMP(NOW())),
            (7, 'AA012347', '4568913253', 536443200000, '(043) 12-34-58', '7 Kievyan, Yerevan', 1, UNIX_TIMESTAMP(NOW())),
            (8, 'AA012348', '4568913254', 915134400000, '(043) 12-34-59', '8 Kievyan, Yerevan', 1, UNIX_TIMESTAMP(NOW())),
            (9, 'AA012349', '4568913255', 599601600000, '(043) 12-34-50', '9 Kievyan, Yerevan', 2, UNIX_TIMESTAMP(NOW()));
            
 INSERT INTO `mydb`.`ADMIN` (`FIRSTNAME`, `LASTNAME`, `EMAIL`, `PASSWORD`, `SCHOOL_ID`, `CREATION_DATE`) 
      VALUES('Yeva', '', 'yeva@gmail.com', 'yeva.password',  1, UNIX_TIMESTAMP(NOW())),
            ('Zvart', 'Manukyan', 'zvart.manukyan@gmail.com', 'zvart.password', 1, UNIX_TIMESTAMP(NOW()));              
            
INSERT INTO `mydb`.`ADMIN_INFO`(`ADMIN_ID`, `PASSPORT_ID`, `SOCIAL_CARD_ID`, `BIRTH_DATE`, `PHONE_NUMBER`, `ADDRESS`, `GENDER`, `CREATION_DATE`)
	  VALUES(1, 'AD012345', '1235489546', 694213200000, '(055) 12-34-56', '12 Kievyan, Yerevan', 2, UNIX_TIMESTAMP(NOW())), 
            (2, 'AD012346', '5459849848', 757371600000, '(077) 12-34-57', '15 Kievyan, Yerevan', 2, UNIX_TIMESTAMP(NOW()));
                                      
INSERT INTO `mydb`.`CLASSROOM` (`SCHOOL_ID`, `NAME`, `CAPACITY`, `TYPE`, `SUBJECT`, `CREATION_DATE`) 
	  VALUES (1, 'Room 1', 14, 1, 'General', UNIX_TIMESTAMP(NOW())),          
             (1, 'Room 2', 20, 1, 'General', UNIX_TIMESTAMP(NOW())),
             (1, 'Yellow Room', 6, 1, 'General', UNIX_TIMESTAMP(NOW())),
			 (2, '1', 10, 2, 'Laboratory', UNIX_TIMESTAMP(NOW())),          
             (2, '2', 10, 1, 'General', UNIX_TIMESTAMP(NOW())),
             (2, '3', 15, 1, 'General', UNIX_TIMESTAMP(NOW())),
			 (3, 'Room 1', 5, 1, 'General', UNIX_TIMESTAMP(NOW())),          
             (3, 'Room 2', 25, 1, 'General', UNIX_TIMESTAMP(NOW())),
             (3, 'Room 3', 12, 1, 'General', UNIX_TIMESTAMP(NOW()));
             
 INSERT INTO `mydb`.`COURSE`(`NAME`, `DESCRIPTION`, `DURATION`, `DURATION_UNIT_TYPE`, `SCHOOL_ID`, `CREATION_DATE`) 
      VALUES('JAVA', 'This is a course about JAVA programming language', 4, 3, 1, UNIX_TIMESTAMP(NOW())),
            ('C++', 'This is a course about C++ programming language', 6, 3, 1, UNIX_TIMESTAMP(NOW())),
            ('PYTHON', 'PYTHON programming language introduction', 24, 2, 2, UNIX_TIMESTAMP(NOW())),
            ('C#', 'C# programming language advanced course', 5, 3, 2, UNIX_TIMESTAMP(NOW())),
            ('JAVA', 'JAVA course', 4, 3, 3, UNIX_TIMESTAMP(NOW())),
            ('JS', 'JS programming language', 4, 3, 3, UNIX_TIMESTAMP(NOW())),
            ('QA', 'Quality assurance introduction', 12, 2, 1, UNIX_TIMESTAMP(NOW())),
            ('UI/UX', 'UI/UX course', 4, 3, 1, UNIX_TIMESTAMP(NOW()));
    
INSERT INTO `mydb`.`LESSON`(`NAME`, `CONTENT`, `CREATION_DATE`, `COURSE_ID`) 
      VALUES('Collections', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('Multithreading', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('JVM', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('OOP', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('Spring', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('Spring MVC', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('Spring Boot', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('JDBC', 'Content', UNIX_TIMESTAMP(NOW()), 1),
            ('MySQL', 'Content', UNIX_TIMESTAMP(NOW()), 1);

INSERT INTO `mydb`.`TOPIC`(`VIDEO_URL`, `WEB_PAGE_URL`, `CREATION_DATE`, `LESSON_ID`) 
      VALUES('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 1),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 2),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 3),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 4),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 5),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 6),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 7),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 8),
			('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 9),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 4),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 5),
            ('https://www.youtube.com/watch?v=Xft40ZQI2rw', 'https://www.youtube.com/watch?v=Xft40ZQI2rw', UNIX_TIMESTAMP(NOW()), 8);
            
INSERT INTO `mydb`.`STUDENT_COMMENT`(`COMMENT`, `CREATION_DATE`, `TOPIC_ID`, `STUDENT_ID`) 
      VALUES('Comment 1', UNIX_TIMESTAMP(NOW()), 1, 6),
            ('Comment 2', UNIX_TIMESTAMP(NOW()), 2, 6),
            ('Comment 3', UNIX_TIMESTAMP(NOW()), 3, 6),
            ('Comment 4', UNIX_TIMESTAMP(NOW()), 1, 7),
            ('Comment 5', UNIX_TIMESTAMP(NOW()), 2, 7),
            ('Comment 6', UNIX_TIMESTAMP(NOW()), 3, 7),
            ('Comment 7', UNIX_TIMESTAMP(NOW()), 4, 8),
            ('Comment 8', UNIX_TIMESTAMP(NOW()), 6, 8),
            ('Comment 9', UNIX_TIMESTAMP(NOW()), 8, 9),
            ('Comment 10', UNIX_TIMESTAMP(NOW()), 2, 9),
            ('Comment 11', UNIX_TIMESTAMP(NOW()), 2, 7),
            ('Comment 12', UNIX_TIMESTAMP(NOW()), 7, 6),
            ('Comment 13', UNIX_TIMESTAMP(NOW()), 2, 8),
            ('Comment 14', UNIX_TIMESTAMP(NOW()), 1, 9),
            ('Comment 15', UNIX_TIMESTAMP(NOW()), 7, 9),
            ('Comment 16', UNIX_TIMESTAMP(NOW()), 7, 7),
            ('Comment 17', UNIX_TIMESTAMP(NOW()), 5, 8),
            ('Comment 18', UNIX_TIMESTAMP(NOW()), 7, 6);            

INSERT INTO `mydb`.`GROUP`(`NAME`, `CREATION_DATE`, `SCHOOL_ID`) 
      VALUES('Java group', UNIX_TIMESTAMP(NOW()), 1),
            ('C# 2019', UNIX_TIMESTAMP(NOW()), 2),
            ('JS group 2019', UNIX_TIMESTAMP(NOW()), 3),
            ('QA', UNIX_TIMESTAMP(NOW()), 1),
            ('Python 2019', UNIX_TIMESTAMP(NOW()), 2);

INSERT INTO `mydb`.`STUDENT_GROUP`(`STUDENT_ID`, `GROUP_ID`, `ADMISSION_DATE`, `CREATION_DATE`)
      VALUES(6, 1, 1546977600000, UNIX_TIMESTAMP(NOW())),
			(7, 1, 1546977600000, UNIX_TIMESTAMP(NOW())),
            (8, 1, 1546977600000, UNIX_TIMESTAMP(NOW())),
            (9, 1, 1546977600000, UNIX_TIMESTAMP(NOW()));
            
INSERT INTO `mydb`.`GROUP_COURSE`(`GROUP_ID`, `COURSE_ID`, `TEACHER_ID`, `START_DATE`, `CREATION_DATE`) 
      VALUES(1, 1, 1, 1546545600000, UNIX_TIMESTAMP(NOW())),
            (2, 4, 2, 1546632000000, UNIX_TIMESTAMP(NOW())),
            (3, 6, 3, 1546718400000, UNIX_TIMESTAMP(NOW())),
            (4, 7, 4, 1546804800000, UNIX_TIMESTAMP(NOW())),
            (5, 3, 5, 1546372800000, UNIX_TIMESTAMP(NOW()));
            
INSERT INTO `mydb`.`TEST`(`TITLE`, `LESSON_ID`, `CREATION_DATE`) 
      VALUES ('Introduction test', 1, UNIX_TIMESTAMP(NOW()));

INSERT INTO `mydb`.`QUESTION`(`CONTENT`, `TEST_ID`, `CREATION_DATE`) 
      VALUES ('Light year is related to', 1, UNIX_TIMESTAMP(NOW())),
             ('Which of the following instruments is used to measure the pressure of gases?', 1, UNIX_TIMESTAMP(NOW())),
             ('Joule is the unit of', 1, UNIX_TIMESTAMP(NOW())),
             ('The unit of current is', 1, UNIX_TIMESTAMP(NOW()));

INSERT INTO `mydb`.`ANSWER`(`QUESTION_ID`, `CONTENT`, `SCORE`, `CREATION_DATE`)
       VALUES (1, 'Energy', 0, UNIX_TIMESTAMP(NOW())),
              (1, 'Speed', 0, UNIX_TIMESTAMP(NOW())),
              (1, 'Distance', 1, UNIX_TIMESTAMP(NOW())),
              (1, 'Time', 0, UNIX_TIMESTAMP(NOW())),

              (2, 'Voltmeter', 0, UNIX_TIMESTAMP(NOW())),
			  (2, 'Manometer', 1, UNIX_TIMESTAMP(NOW())),
              (2, 'Ammeter', 0, UNIX_TIMESTAMP(NOW())),
              (2, 'None', 0, UNIX_TIMESTAMP(NOW())),

              (3, 'Temperature', 0, UNIX_TIMESTAMP(NOW())),
              (3, 'Pressure', 0, UNIX_TIMESTAMP(NOW())),
              (3, 'Energy', 1, UNIX_TIMESTAMP(NOW())),
              (3, 'Heat', 0, UNIX_TIMESTAMP(NOW())),

              (4, 'Ohm', 0, UNIX_TIMESTAMP(NOW())),
              (4, 'Watt', 0, UNIX_TIMESTAMP(NOW())),
              (4, 'Ampere', 1, UNIX_TIMESTAMP(NOW())),
              (4, 'None', 0, UNIX_TIMESTAMP(NOW()));        

INSERT INTO `mydb`.`TEST_RESULT` (`TEST_ID`, `STUDENT_ID`, `SCORE`, `CREATION_DATE`, `TAKEN_DATE`)  
	  VALUES (1, 6, 9, UNIX_TIMESTAMP(NOW()), 1557864000000), 
			 (1, 7, 8, UNIX_TIMESTAMP(NOW()), 1557864000000), 
			 (1, 8, 10, UNIX_TIMESTAMP(NOW()), 1557864000000),
			 (1, 9, 8, UNIX_TIMESTAMP(NOW()), 1557864000000);

INSERT INTO `mydb`.`ASSIGNMENT`(`START_DATE`, `END_DATE`, `TITLE`, `DESCRIPTION`, `CREATION_DATE`, `TEACHER_ID`, `LESSON_ID`) 
      VALUES(1472583699879, 1479583699879, 'ASSIGNMENT 1', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 1),
            (1472583699879, 1479583699879, 'ASSIGNMENT 2', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 2),
            (1472583699879, 1479583699879, 'ASSIGNMENT 3', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 3),
            (1472583699879, 1479583699879, 'ASSIGNMENT 4', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 4),
            (1472583699879, 1479583699879, 'ASSIGNMENT 5', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 5),
            (1472583699879, 1479583699879, 'ASSIGNMENT 6', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 6),
            (1472583699879, 1479583699879, 'ASSIGNMENT 7', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 7),
            (1472583699879, 1479583699879, 'ASSIGNMENT 8', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 8),
            (1472583699879, 1479583699879, 'ASSIGNMENT 9', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 9),
            (1472583699879, 1479583699879, 'ASSIGNMENT 10', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 1),
            (1472583699879, 1479583699879, 'ASSIGNMENT 11', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 5),
            (1472583699879, 1479583699879, 'ASSIGNMENT 12', 'DEDCRIPTION', UNIX_TIMESTAMP(NOW()), 1, 3);
            

-- INSERT INTO `mydb`.`ASSIGNMENT_COMPLETED`(`STUDENT_ID`, `ASSIGNMENT_ID`, `CONTENT`, CREATION_DATE) 
--       VALUES(6, 1, 'ASSIGNMENT 1', UNIX_TIMESTAMP(NOW())),
--             (6, 6, 'ASSIGNMENT 2', UNIX_TIMESTAMP(NOW())),
--             (6, 11, 'ASSIGNMENT 3', UNIX_TIMESTAMP(NOW())),
--             (7, 2, 'ASSIGNMENT 4', UNIX_TIMESTAMP(NOW())),
-- 			(7, 7, 'ASSIGNMENT 5', UNIX_TIMESTAMP(NOW())),
--             (7, 12, 'ASSIGNMENT 6', UNIX_TIMESTAMP(NOW())),
--             (8, 3, 'ASSIGNMENT 7', UNIX_TIMESTAMP(NOW())),
--             (8, 8, 'ASSIGNMENT 8', UNIX_TIMESTAMP(NOW())),
--             (8, 4, 'ASSIGNMENT 9', UNIX_TIMESTAMP(NOW())),
--             (9, 9, 'ASSIGNMENT 10', UNIX_TIMESTAMP(NOW())),
--             (9, 5, 'ASSIGNMENT 11', UNIX_TIMESTAMP(NOW())),
--             (9, 10, 'ASSIGNMENT 12', UNIX_TIMESTAMP(NOW()));

INSERT INTO `mydb`.`ASSESSMENT`(`SCORE`, `CREATION_DATE`, `STUDENT_ID`, `ASSIGNMENT_COMPLETED_ID`, `COMMENT`) 
      VALUES(10, UNIX_TIMESTAMP(NOW()), 6, 29, 'Cmment 1'),
			(8, UNIX_TIMESTAMP(NOW()), 7, 30, 'Cmment 2'),
			(0, UNIX_TIMESTAMP(NOW()), 8, 31, 'Cmment 3'),
			(7, UNIX_TIMESTAMP(NOW()), 9, 11, 'Cmment 4'),
			(8, UNIX_TIMESTAMP(NOW()), 6, 1, 'Cmment 5'),
			(9, UNIX_TIMESTAMP(NOW()), 7, 37, 'Cmment 6'),
			(4, UNIX_TIMESTAMP(NOW()), 9, 25, 'Cmment 8'),
			(6, UNIX_TIMESTAMP(NOW()), 6, 50, 'Cmment 9'),
			(5, UNIX_TIMESTAMP(NOW()), 7, 16, 'Cmment 10'),
			(8, UNIX_TIMESTAMP(NOW()), 8, 24, 'Cmment 11'),
			(9, UNIX_TIMESTAMP(NOW()), 9, 32, 'Cmment 12'),
			(1, UNIX_TIMESTAMP(NOW()), 6, 36, 'Cmment 13'),
			(3, UNIX_TIMESTAMP(NOW()), 8, 10, 'Cmment 15'),
			(5, UNIX_TIMESTAMP(NOW()), 9, 60, 'Cmment 16'),
			(6, UNIX_TIMESTAMP(NOW()), 6, 15, 'Cmment 17');  
            
INSERT INTO `mydb`.`SURVEY_RESULT` (`STUDENT_ID`, `EXT`, `EST`, `AGR`, `CSN`, `OPN`, `CREATION_DATE`) 
	  VALUES (7, 5.0, 3.6, 4.3, 5.0, 1.4, UNIX_TIMESTAMP(NOW()));               
            
INSERT INTO `mydb`.`SCHEDULE_RECORD` (`GROUP_COURSE_ID`, `CLASSROOM_ID`, `LESSON_ID`, `START_DATE`, `END_DATE`, `CREATION_DATE`) 
	  VALUES (1, 3, 1, 1546678800000, 1546685100000, UNIX_TIMESTAMP(NOW()));            

