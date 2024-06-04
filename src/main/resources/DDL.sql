use hungry;
create database hungry;

select * from user;

select * from store;

SELECT *
FROM store
WHERE LOWER(store_Name) LIKE '%구로%' OR LOWER(store_Address) LIKE '%k구로%';


SELECT *
FROM store
WHERE LOWER(store_name) LIKE '%구로%' OR LOWER(store_address) LIKE '%구로%';


SELECT * FROM store s WHERE lower(s.store_name) LIKE '%구로%' OR lower(s.store_address) LIKE '%구로%';

INSERT INTO store (store_name, store_address, store_latitude, store_longitude, store_tel, store_description)
VALUES ('김밥천국', '서울특별시 강남구 테헤란로 123', '37.496345', '127.029572', '02-1234-5678', '다양한 김밥과 간단한 식사를 제공하는 김밥 전문점입니다.');

INSERT INTO store (store_name, store_address, store_latitude, store_longitude, store_tel, store_description)
VALUES ('스타벅스', '서울특별시 종로구 종로 456', '37.570388', '126.983716', '02-9876-5432', '편안한 분위기에서 커피와 음료를 즐길 수 있는 카페입니다.');

select * from party;

INSERT INTO party (party_count, party_limit, party_title, party_time, party_start_time, party_end_time, store_id)
VALUES (3, 5, '강남 김밥천국 점심 모임', '점심', '2024-06-01 11:45:00', '2024-06-01 13:00:00', 1);

INSERT INTO party (party_count, party_limit, party_title, party_time, party_start_time, party_end_time, store_id)
VALUES (2, 4, '아침 같이 먹으실 분 구합니다~~', '아침', '2024-06-02 08:45:00', '2024-06-02 10:00:00', 1);

INSERT INTO party (party_count, party_limit, party_title, party_time, party_start_time, party_end_time, store_id)
VALUES (4, 10, '종로 스타벅스 저녁 모임', '저녁', '2024-06-01 17:45:00', '2024-06-01 19:30:00', 2);

INSERT INTO party (party_count, party_limit, party_title, party_time, party_start_time, party_end_time, store_id)
VALUES (5, 8, '아침 독서 모임 하실분!!!!!', '아침', '2024-06-03 07:45:00', '2024-06-03 09:30:00', 2);


INSERT INTO user (user_email, user_password, user_type, user_name, user_tel, user_profile_img, user_nickname, user_role)
VALUES ('user1@example.com', 'password123', 'app', '홍길동', '010-1234-5678', 'profile1.png', '길동이', 'ROLE_USER');

INSERT INTO user (user_email, user_password, user_type, user_name, user_tel, user_profile_img, user_nickname, user_role)
VALUES ('user2@example.com', 'password456', 'kakao', '김철수', '010-2345-6789', 'profile2.png', '철수', 'ROLE_USER');

INSERT INTO user (user_email, user_password, user_type, user_name, user_tel, user_profile_img, user_nickname, user_role)
VALUES ('user3@example.com', 'password789', 'app', '이영희', '010-3456-7890', 'profile3.png', '영희', 'ROLE_USER');


-- 강남 김밥천국 점심 모임 (partyId 1) - 현재 참가자 3명
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user1@example.com', 1, 1);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user2@example.com', 1, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user3@example.com', 1, 0);

-- 종로 스타벅스 저녁 모임 (partyId 2) - 현재 참가자 4명
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user1@example.com', 2, 1);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user2@example.com', 2, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user3@example.com', 2, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym2615@naver.com', 2, 0);

-- 강남 김밥천국 아침 모임 (partyId 3) - 현재 참가자 2명
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym2615@naver.com', 3, 1);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym26154@naver.com', 3, 0);

-- 종로 스타벅스 아침 독서 모임 (partyId 4) - 현재 참가자 5명
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user1@example.com', 4, 1);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user2@example.com', 4, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('user3@example.com', 4, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym2615@naver.com', 4, 0);
INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym26154@naver.com', 4, 0);

-- 강남 김밥천국 점심 모임 (partyId 1) - 메시지 데이터
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user1@example.com', 1, '점심 메뉴는 무엇으로 할까요?', '2024-05-31 12:00:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user2@example.com', 1, '김밥이 좋을 것 같아요.', '2024-05-31 12:01:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user3@example.com', 1, '저는 라면도 괜찮아요.', '2024-05-31 12:02:00');

-- 종로 스타벅스 저녁 모임 (partyId 2) - 메시지 데이터
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user1@example.com', 2, '모임 시간에 늦지 않도록 합시다.', '2024-05-31 18:00:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user2@example.com', 2, '몇 시에 모일까요?', '2024-05-31 18:01:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user3@example.com', 2, '저녁 7시가 좋겠어요.', '2024-05-31 18:02:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym2615@naver.com', 2, '저도 7시에 맞출게요.', '2024-05-31 18:03:00');

-- 강남 김밥천국 아침 모임 (partyId 3) - 메시지 데이터
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym2615@naver.com', 3, '아침 모임에서 무엇을 할까요?', '2024-05-31 08:00:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym26154@naver.com', 3, '운동 후에 식사를 하는 건 어떨까요?', '2024-05-31 08:01:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym2615@naver.com', 3, '좋습니다 그 때 뵙죠!', '2024-05-31 08:01:35');

-- 종로 스타벅스 아침 독서 모임 (partyId 4) - 메시지 데이터
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user1@example.com', 4, '독서 모임에서 읽을 책을 정합시다.', '2024-05-31 09:00:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user2@example.com', 4, '어떤 책이 좋을까요?', '2024-05-31 09:01:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('user3@example.com', 4, '저는 소설책을 추천합니다.', '2024-05-31 09:02:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym2615@naver.com', 4, '소설책 좋은 생각이에요.', '2024-05-31 09:03:00');
INSERT INTO message (user_email, party_id, content, send_time) VALUES ('leeym26154@naver.com', 4, '모두가 즐길 수 있는 책이면 좋겠어요.', '2024-05-31 09:04:00');

select * from message;
select * from party;
select * from party_member;
delete from party_member where party_id = 1 and user_email = 'leeym26154@naver.com';

select * from party where party_id in (select party_id from party_member where user_email = 'leeym26154@naver.com');

INSERT INTO party_member (user_email, party_id, member_role) VALUES ('leeym2615@naver.com', 5, 0);