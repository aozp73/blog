create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    created_at timestamp
);

create table board_tb(
    id int auto_increment primary key,
    user_id int not null,
    title varchar not null,
    content longtext not null,
    created_at timestamp
);

create table love_tb(

);

create table reply_tb(
    
);