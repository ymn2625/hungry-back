use hungry;

select * from user;
delete from user;



SELECT *
FROM store
WHERE LOWER(store_Name) LIKE '%구로%' OR LOWER(store_Address) LIKE '%k구로%';


SELECT *
FROM store
WHERE LOWER(store_name) LIKE '%구로%' OR LOWER(store_address) LIKE '%구로%';


SELECT * FROM store s WHERE lower(s.store_name) LIKE '%구로%' OR lower(s.store_address) LIKE '%구로%';

