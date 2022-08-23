use db;

create table user (
    id int not null auto_increment,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(20),
    password varchar(256),
    primary key(id),
    unique key (email)
);

create table role (
    id int not null auto_increment,
    name varchar(20),
    primary key(id)
);

create table user_role(
  user_id int,
  role_id int,
  foreign key (user_id) references user(id),
  foreign key (role_id) references role(id),
  unique key(user_id,role_id)
);

insert into user(id,first_name,last_name,email,password) values ('1','david','chow','ykchowaa@gmail.com','');
insert into role(id,name) values (1,'Admin');
insert into user_role(user_id,role_id) values (1,1);