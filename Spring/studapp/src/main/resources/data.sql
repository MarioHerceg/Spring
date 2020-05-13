delete from student;

insert into student (ID, first_name, last_name, jmbag, ects_points, date_of_birth)
                values (1, 'Ivo', 'Ivić', '0256423323', 120, NOW());
insert into student (ID, first_name, last_name, jmbag, ects_points, date_of_birth)
                values (2, 'Ivo', 'Lucić', '0256423324', 99, NOW());
insert into student (ID, first_name, last_name, jmbag, ects_points, date_of_birth)
                values (3, 'Lucija', 'Ivić', '0256423325', 115, NOW());

insert into course(id, naziv, ects_points)
                values (1, 'Web aplikacije u Javi', 6);
insert into course(id, naziv, ects_points)
                values (2, 'Programiranje u jeziku Java', 5);
insert into course(id, naziv, ects_points)
                values (3, 'Programiranje u Androidu', 7);

insert into student_course (id, student_id, course_id)
                values (1, 1, 1);
insert into student_course (id, student_id, course_id)
                values (2, 2, 1);
insert into student_course (id, student_id, course_id)
                values (3, 2, 2);
insert into student_course (id, student_id, course_id)
                values (4, 3, 3);