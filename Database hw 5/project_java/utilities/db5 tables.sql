create table Student(
StudentId int,
StudentName char(30) NOT NULL,
Major char(30) NOT NULL,
PRIMARY KEY(StudentId)
);

CREATE TABLE Course(
DeptCode int,
CourseNum int,
Title char(50) NOT NULL,
CreditHours int NOT NULL,
Primary Key(DeptCode,CourseNum)
);

Create Table Enrollment(
StudID int, 
DeptC int,
CourNUM int,
Foreign key (StudID) References Student(StudentId),
Foreign key (DeptC, courNUM) References Course(DeptCode, courseNum)
);


