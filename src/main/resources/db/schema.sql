use db;

create table coupon (
    id int auto_increment,
    name varchar(20),
    description varchar(100),
    price decimal(8,3),
    primary key(id)
);

create table coupon (
    id int auto_increment,
    code varchar(20),
    discount decimal(8,3),
    exp_date varchar(100),
    primary key(id)
);