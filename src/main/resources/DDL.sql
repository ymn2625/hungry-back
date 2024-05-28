use hungry;

select * from user;
delete from user;
update user set user_tel='01012341111' where user_id = 26;

select * from store;

INSERT INTO store VALUES(null, 'address', 'description', 'latotude', 'longtitude', 'name', 'tel');

SELECT *
FROM store
WHERE LOWER(store_Name) LIKE '%구로%' OR LOWER(store_Address) LIKE '%k구로%';


SELECT *
FROM store
WHERE LOWER(store_name) LIKE '%구로%' OR LOWER(store_address) LIKE '%구로%';


SELECT * FROM store s WHERE lower(s.store_name) LIKE '%구로%' OR lower(s.store_address) LIKE '%구로%';


