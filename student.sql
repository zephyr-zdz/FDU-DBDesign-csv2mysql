CREATE TABLE IF NOT EXISTS student
(
    registno int         not null,
    name     varchar(10) not null,
    kdno     int         not null,
    kcno     int         not null,
    ccno     int         not null,
    seat     int         not null,
    constraint student_pk
        primary key (registno)
);
