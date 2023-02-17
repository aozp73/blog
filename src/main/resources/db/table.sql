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
    love_cnt int,
    created_at timestamp
);

create table love_tb(
    id int auto_increment primary key,
    board_id int not null,
    board_user_id int not null,
    acted_user_id int not null,
    is_check boolean default false,
    created_at timestamp
);

create table reply_tb(
    id int auto_increment primary key,
    board_id int not null,
    user_id int not null,
    username varchar not null,
    content longtext not null,
    created_at timestamp
);