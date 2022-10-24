create table if not exists room
(
    kdno      int          not null,
    kcno      int          not null,
    ccno      int          not null,
    kdname    varchar(128) not null,
    exptime   varchar(128) not null,
    papername varchar(128) null,
    primary key (kdno, kcno, ccno)
);
