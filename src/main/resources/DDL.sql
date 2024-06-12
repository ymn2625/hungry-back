use hungry;
create database hungry;

SELECT *
FROM store
WHERE LOWER(store_Name) LIKE '%구로%' OR LOWER(store_Address) LIKE '%k구로%';

SELECT *
FROM store
WHERE LOWER(store_name) LIKE '%구로%' OR LOWER(store_address) LIKE '%구로%';

SELECT * FROM store s WHERE lower(s.store_name) LIKE '%구로%' OR lower(s.store_address) LIKE '%구로%';

INSERT INTO user (user_email, user_password, user_type, user_name, user_tel, user_profile_img, user_nickname, user_role)
VALUES
    ('example1@gmail.com', 'password1', 'app', '홍길동', '010-1234-5678', 'profile1.jpg', '길동이', 'ROLE_USER'),
    ('example2@gmail.com', 'password2', 'naver', '이몽룡', '010-2345-6789', 'profile2.jpg', '몽룡이', 'ROLE_USER'),
    ('example3@gmail.com', 'password3', 'kakao', '성춘향', '010-3456-7890', 'profile3.jpg', '춘향이', 'ROLE_USER'),
    ('example4@gmail.com', 'password4', 'app', '박문수', '010-4567-8901', 'profile4.jpg', '문수', 'ROLE_USER'),
    ('example5@gmail.com', 'password5', 'naver', '변학도', '010-5678-9012', 'profile5.jpg', '학도', 'ROLE_USER'),
    ('example6@gmail.com', 'password6', 'kakao', '임꺽정', '010-6789-0123', 'profile6.jpg', '꺽정이', 'ROLE_USER'),
    ('example7@gmail.com', 'password7', 'app', '손오공', '010-7890-1234', 'profile7.jpg', '오공', 'ROLE_USER'),
    ('example8@gmail.com', 'password8', 'naver', '전우치', '010-8901-2345', 'profile8.jpg', '우치', 'ROLE_USER'),
    ('example9@gmail.com', 'password9', 'kakao', '홍길순', '010-9012-3456', 'profile9.jpg', '길순이', 'ROLE_USER'),
    ('example10@gmail.com', 'password10', 'app', '이순신', '010-0123-4567', 'profile10.jpg', '순신이', 'ROLE_USER');


INSERT INTO store (store_name, store_address, store_latitude, store_longitude, store_tel, store_description)
VALUES
    ('맛있는 떡볶이', '서울시 강남구 역삼동 123-45', '37.505665', '127.036426', '02-1234-5678', '매콤하고 달콤한 떡볶이를 즐길 수 있는 가게입니다.'),
    ('행복한 카페', '서울시 마포구 서교동 678-90', '37.553442', '126.918977', '02-2345-6789', '편안한 분위기와 맛있는 음료를 즐길 수 있는 카페입니다.'),
    ('새콤달콤 과일가게', '서울시 송파구 거여동 321-54', '37.493679', '127.137877', '02-3456-7890', '신선한 과일을 취급하는 가게입니다.'),
    ('고기야', '서울시 강북구 수유동 987-65', '37.639781', '127.025994', '02-4567-8901', '신선한 고기와 각종 안주를 즐길 수 있는 가게입니다.'),
    ('해산물 소리소', '서울시 강서구 마곡동 789-01', '37.559580', '126.820945', '02-5678-9012', '신선한 해산물을 맛볼 수 있는 가게입니다.');

INSERT INTO party (party_count, party_limit, party_name, party_description, party_img, party_time, party_start_time, party_end_time, store_id)
VALUES
    (2, 10, '맛있는 떡볶이 파티', '맛있는 떡볶이를 함께 먹고 싶은 사람들을 모집합니다.', 'party1.jpg', '점심', '2024-06-09 12:00:00', '2024-06-09 13:30:00', 1),
    (2, 8, '행복한 카페 모임', '카페에서 즐거운 시간을 보내고 싶은 분들을 모집합니다.', 'party2.jpg', '저녁', '2024-06-09 18:00:00', '2024-06-09 20:00:00', 2),
    (3, 12, '과일가게 소모임', '신선한 과일을 함께 공유하고 즐기는 소모임을 엽니다.', 'party3.jpg', '아침', '2024-06-10 09:00:00', '2024-06-10 10:30:00', 3),
    (2, 6, '고기야 파티', '신선한 고기와 함께하는 파티를 엽니다.', 'party4.jpg', '저녁', '2024-06-10 19:00:00', '2024-06-10 21:00:00', 4),
    (3, 10, '해산물 소모임', '신선한 해산물을 즐기고 이야기를 나누는 시간을 가집니다.', 'party5.jpg', '점심', '2024-06-11 13:00:00', '2024-06-11 14:30:00', 5),
    (2, 8, '떡볶이 조지는 파티', '마음껏 치킨을 먹고 즐거운 시간을 보내는 파티입니다.', 'party6.jpg', '저녁', '2024-06-11 18:30:00', '2024-06-11 20:30:00', 1),
    (3, 15, '커피 모임', '맛있는 커피와 함께하는 소소한 모임을 엽니다.', 'party7.jpg', '아침', '2024-06-12 10:00:00', '2024-06-12 11:30:00', 2);

INSERT INTO party_member (user_email, party_id, member_role)
VALUES
    ('example1@gmail.com', 1, 1), -- party_id 1
    ('example2@gmail.com', 1, 0), -- party_id 1

    ('example3@gmail.com', 2, 1), -- party_id 2
    ('example4@gmail.com', 2, 0), -- party_id 2

    ('example5@gmail.com', 3, 1), -- party_id 3
    ('example6@gmail.com', 3, 0), -- party_id 3
    ('example7@gmail.com', 3, 0), -- party_id 3

    ('example8@gmail.com', 4, 0), -- party_id 4
    ('example9@gmail.com', 4, 0), -- party_id 4

    ('example10@gmail.com', 5, 1), -- party_id 5
    ('example1@gmail.com', 5, 0), -- party_id 5
    ('leeym26154@naver.com', 5, 0), -- party_id 5

    ('example3@gmail.com', 6, 1), -- party_id 6
    ('example4@gmail.com', 6, 0), -- party_id 6

    ('example5@gmail.com', 7, 1), -- party_id 7
    ('example6@gmail.com', 7, 0), -- party_id 7
    ('leeym26154@naver.com', 7, 0); -- party_id 7

INSERT INTO message (
    user_email,
    party_id,
    content,
    send_time
) VALUES
      ('example1@gmail.com', 1, '안녕하세요. 김밥 아침 모임에 참여하겠습니다.', '2024-06-10 08:30:00'),
      ('example2@gmail.com', 1, '안녕하세요. 저도 김밥 아침 모임에 참여할게요!', '2024-06-10 08:45:00'),
      ('example1@gmail.com', 1, '반갑습니다!', '2024-06-10 09:00:00'),
      ('example3@gmail.com', 2, '순대국 점심 모임에 참여할게요.', '2024-06-10 12:15:00'),
      ('example4@gmail.com', 2, '네, 저도 참여할게요!', '2024-06-10 12:30:00'),
      ('example3@gmail.com', 2, '순대국 맛있겠네요. 함께해요!', '2024-06-10 12:45:00'),
      ('example4@gmail.com', 2, '네, 갈게요!', '2024-06-10 13:00:00'),
      ('example5@gmail.com', 3, '비빔밥 저녁 모임에 참여하겠습니다.', '2024-06-10 18:15:00'),
      ('example6@gmail.com', 3, '저도 비빔밥 저녁 모임에 참여할게요.', '2024-06-10 18:30:00'),
      ('example8@gmail.com', 4, '백반 아침 모임에 참여할게요.', '2024-06-10 08:45:00'),
      ('example9@gmail.com', 4, '네, 백반 아침 모임에요!', '2024-06-10 09:00:00'),
      ('example8@gmail.com', 4, '안녕하세요.', '2024-06-10 09:15:00'),
      ('example8@gmail.com', 4, '함께해요!', '2024-06-10 09:30:00'),
      ('example10@gmail.com', 5, '곱창 저녁 모임에 참여할게요.', '2024-06-10 19:15:00'),
      ('example1@gmail.com', 5, '좋아요. 함께하죠!', '2024-06-10 19:30:00'),
      ('leeym26154@naver.com', 5, '안녕하세요!', '2024-06-10 19:45:00'),
      ('leeym26154@naver.com', 5, '곱창 맛있겠네요. 기대돼요!', '2024-06-10 20:00:00'),
      ('example3@gmail.com', 6, '전통한식 점심 모임에 참여하겠습니다.', '2024-06-11 12:30:00'),
      ('example4@gmail.com', 6, '네, 저도 갈게요!', '2024-06-11 12:45:00'),
      ('example5@gmail.com', 7, '분식 아침 모임에 참여할게요.', '2024-06-11 08:15:00'),
      ('example6@gmail.com', 7, '저두요~', '2024-06-11 08:16:00'),
      ('leeym26154@naver.com', 7, '맛있겠네요.', '2024-06-11 08:18:00');


SELECT p.*, m.content FROM (SELECT party_id, MAX(send_time) AS max_send_time FROM message GROUP BY party_id) AS latest_messages JOIN message AS m ON latest_messages.party_id = m.party_id AND latest_messages.max_send_time = m.send_time JOIN party AS p ON m.party_id = p.party_id JOIN party_member AS pm ON pm.party_id = p.party_id WHERE pm.user_email = 'leeym26154@naver.com';
SELECT
    p.store_id storeId,
    p.party_id partyId,
    p.party_limit partyLimit,
    p.party_count partyCount,
    p.party_name partyName,
    p.party_description partyDescription,
    p.party_time partyTime,
    p.party_start_time partyStartTime,
    p.party_end_time partyEndTime,
    p.party_img partyImg,
    m.content
FROM (SELECT party_id, MAX(send_time) AS max_send_time FROM message GROUP BY party_id) AS latest_messages
JOIN message AS m ON latest_messages.party_id = m.party_id AND latest_messages.max_send_time = m.send_time
JOIN party AS p ON m.party_id = p.party_id JOIN party_member AS pm ON pm.party_id = p.party_id
WHERE pm.user_email = 'leeym26154@naver.com';


select * from message order by party_id, send_time desc;

