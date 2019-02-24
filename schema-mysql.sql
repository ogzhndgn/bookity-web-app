create table user
(
  id           int auto_increment
    primary key,
  mailaddress  varchar(256) null,
  passwordhash varchar(512) null,
  passwordsalt varchar(64)  null
);

create table book
(
  id       int auto_increment
    primary key,
  title    varchar(512) null,
  author   varchar(512) null,
  category varchar(256) null,
  isbn     varchar(16)  null
);