DROP TABLE IF EXISTS student;
CREATE TABLE student (
ID identity,
first_name varchar(50) not null,
last_name varchar(250) not null,
jmbag varchar(15) not null,
ects_points int not null,
date_of_birth timestamp,
primary key (ID)
);

DROP TABLE IF EXISTS course;
CREATE TABLE course (
ID identity,
naziv varchar(50) not null,
ects_points int not null,
primary key (ID)
);

DROP TABLE IF EXISTS student_course;
CREATE TABLE student_course (
ID identity,
student_id int not null,
course_id int not null,
primary key (ID),
foreign key (student_id) references student(ID),
foreign key (course_id) references course(ID)
);