insert into user_tb(username, password, email, created_at) values('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at) values('love', '1234', 'love@nate.com', now());

insert into board_tb(user_id, title, content, created_at) values(1, '더미_ssar_title', '더미_ssar_content', now());

insert into reply_tb(board_id, user_id, username, content, created_at) values(1, 1, '더미_username_ssar', '더미_content', now());
commit;