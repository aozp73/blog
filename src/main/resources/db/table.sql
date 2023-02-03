create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    created_at timestamp
);

create table board_tb(
    id int auto_increment primary key,
    user_id int,
    title varchar,
    content varchar,
    created_at timestamp
);

create table love_tb(

);

create table reply_tb(
    
);