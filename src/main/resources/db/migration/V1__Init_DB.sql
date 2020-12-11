create sequence hibernate_sequence start 1 increment 1;

create table usr (
    id int8 not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255),
    activation_code varchar(255),
    active boolean not null,
    primary key (id)
);

create table user_roles (
    user_id int8 not null,
    roles varchar(255)
);

alter table if exists user_roles
    add constraint user_id_fk foreign key (user_id) references usr;