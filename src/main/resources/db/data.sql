insert into user_tb(username, password, email, created_at) values('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at) values('love', '1234', 'love@nate.com', now());

insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글1', '더미_ssar_content1', 5, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글2', '더미_ssar_content2', 20, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글3', '더미_ssar_content3', 11, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글4', '더미_ssar_content4', 3, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글5', '더미_ssar_content5', 7, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글6', '더미_ssar_content6', 9, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글7', '더미_ssar_content7', 55, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글8', '더미_ssar_content8', 31, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글9', '더미_ssar_content9', 2201, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글10', '더미_ssar_content10', 331, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글11', '더미_ssar_content11', 396, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(2, '게시글12', '더미_ssar_content12', 12, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글13', '더미_ssar_content13', 73, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글14', '더미_ssar_content14', 94, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글15', '더미_ssar_content15', 56, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글16', '더미_ssar_content16', 37, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글17', '더미_ssar_content17', 31, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글18', '더미_ssar_content18', 72, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글19', '더미_ssar_content19', 44, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글20', '더미_ssar_content20', 66, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글21', '더미_ssar_content21', 78, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글22', '더미_ssar_content22', 33, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글23', '더미_ssar_content23', 24, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글24', '더미_ssar_content24', 73, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글25', '더미_ssar_content25', 22, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글26', '더미_ssar_content26', 31, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글27', '더미_ssar_content27', 57, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글28', '더미_ssar_content28', 36, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글29', '더미_ssar_content29', 62, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글30', '더미_ssar_content30', 23, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글31', '더미_ssar_content31', 21, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글32', '더미_ssar_content32', 16, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글33', '더미_ssar_content33', 32, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글34', '더미_ssar_content34', 221, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글35', '더미_ssar_content35', 97, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글36', '더미_ssar_content36', 72, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글37', '더미_ssar_content37', 121, now());
insert into board_tb(user_id, title, content, love_cnt, created_at) values(1, '게시글38', '더미_ssar_content38', 92, now());

insert into reply_tb(board_id, user_id, username, content, created_at) values(1, 1, '더미_username_ssar', '더미_content', now());
insert into reply_tb(board_id, user_id, username, content, created_at) values(2, 2, '더미_username_cos', '더미_content', now());

insert into love_tb(board_id, board_user_id, acted_user_id, is_check, created_at) values(1, 1, 1, false, now());
insert into love_tb(board_id, board_user_id, acted_user_id, is_check, created_at) values(2, 2, 1, false, now());

commit;

