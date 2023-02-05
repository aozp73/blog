insert into user_tb(username, password, email, created_at) values('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at) values('love', '1234', 'love@nate.com', now());

insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글5', '더미_ssar_content1', 5, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글20', '더미_ssar_content2', 20, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글11', '더미_ssar_content3', 11, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글3', '더미_ssar_content4', 3, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글7', '더미_ssar_content5', 7, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글9', '더미_ssar_content6', 9, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글55', '더미_ssar_content7', 55, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글31', '더미_ssar_content8', 31, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글2201', '더미_ssar_content9', 2201, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글331', '더미_ssar_content10', 331, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '더미 게시글396', '더미_ssar_content11', 396, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '더미 게시글1125', '더미_ssar_content12', 1125, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '넘어가야함', '더미_ssar_content2', 1652, now());


insert into reply_tb(board_id, user_id, username, content, created_at) values(1, 1, '더미_username_ssar', '더미_content', now());
insert into reply_tb(board_id, user_id, username, content, created_at) values(2, 2, '더미_username_cos', '더미_content', now());

insert into love_tb(board_id, board_user_id, acted_user_id, is_check, created_at) values(1, 1, 1, false, now());
insert into love_tb(board_id, board_user_id, acted_user_id, is_check, created_at) values(2, 2, 1, false, now());

commit;

