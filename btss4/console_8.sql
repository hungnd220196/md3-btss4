use quanlydiembt4;


-- Tạo bảng class
create table class
(
    id        int primary key,
    classname varchar(100),
    startdate date,
    status    bit
);

-- Tạo bảng student với cột class_id
create table student
(
    id          int primary key,
    studentname varchar(100),
    address     varchar(255),
    phone       varchar(11),
    status      bit,
    class_id    int,
    foreign key (class_id) references class (id)
);

-- Tạo bảng subject
create table subject
(
    id          int primary key,
    subjectname varchar(100),
    credit      int,
    status      bit
);

-- Tạo bảng mark
create table mark
(
    id         int primary key,
    student_id int,
    subject_id int,
    point      double,
    examtime   datetime,
    status     bit,
    foreign key (student_id) references student (id),
    foreign key (subject_id) references subject (id)
);


-- Thêm dữ liệu vào bảng class
INSERT INTO class (id, classname, startdate, status)
VALUES (1, 'A1', '2024-01-01', 1),
       (2, 'A2', '2024-01-01', 1);

-- Thêm dữ liệu vào bảng student
INSERT INTO student (id, studentname, address, phone, status, class_id)
VALUES (1, 'John Doe', '123 Main Street', '123456789', 1, 1),
       (2, 'Jane Smith', '456 Elm Street', '987654321', 1, 1),
       (3, 'Alice Johnson', '789 Oak Street', '456789123', 1, 2);

-- Thêm dữ liệu vào bảng subject
INSERT INTO subject (id, subjectname, credit, status)
VALUES (1, 'Toán', 3, 1),
       (2, 'Văn', 2, 1),
       (3, 'Anh', 2, 1);

-- Thêm dữ liệu vào bảng mark
INSERT INTO mark (id, student_id, subject_id, point, examtime, status)
VALUES (1, 1, 1, 8.5, '2024-03-15 08:00:00', 1),
       (2, 1, 2, 7.5, '2024-03-16 09:00:00', 1),
       (3, 1, 3, 9, '2024-03-17 10:00:00', 1),
       (4, 2, 1, 7, '2024-03-15 08:00:00', 1),
       (5, 2, 2, 8, '2024-03-16 09:00:00', 1),
       (6, 2, 3, 8.5, '2024-03-17 10:00:00', 1),
       (7, 3, 1, 6.5, '2024-03-15 08:00:00', 1),
       (8, 3, 2, 6, '2024-03-16 09:00:00', 1),
       (9, 3, 3, 4, '2024-03-17 10:00:00', 1);


-- Hiển thị tất cả học sinh thuộc lớp A1
select *
from student
where class_id = (select id from class where classname = 'A1');

-- Hiển thị tất cả học sinh thi môn "Toán"
select *
from student
where id in (select student_id from mark where subject_id = (select id from subject where subjectname = 'Toán'));

-- Hiển thị tất cả học sinh có điểm trung bình lớn hơn 5
select *
from student
where id in (select student_id from mark group by student_id having avg(point) > 5);

-- Hiển thị các môn học có điểm trung bình dưới 5
select *
from subject
where id in (select subject_id from mark group by subject_id having avg(point) < 5);

-- Hiển thị tất cả học sinh không thi môn nào
select *
from student
where id not in (select student_id from mark);
