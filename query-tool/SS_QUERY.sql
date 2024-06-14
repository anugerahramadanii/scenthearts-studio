select * from m_user

select * from m_user where email = 'john@gmail.com' and password = 'John123123@'

select exists (select * from m_user where email ilike '%john@gmail.com%' and is_delete = false and (is_locked = false or is_locked is null))

with myTable as
(select email, token from token where email ilike 'john@gmail.com' and is_delete = false order by id desc limit 1)
select exists(select * from myTable where token ilike '123456')

with myTable as
(select email, token, expired_on from token where email ilike 'john@gmail.com' and is_delete = false order by id desc limit 1)
select exists(select * from myTable where expired_on < now())


select b.firstname, u.password, b,lastname, b.mobile_phone, b.image, b.image_path, u.email
from m_biodata b 
inner join m_user u on
b.id = u.biodata_id
where u.id = 2

select password from m_user where id = 1 and is_delete = false

select * from t_token

select * from t_category where is_delete = false

select exists (select * from t_category where initial ilike 'AA' and is_delete = false)

select * from t_product

select id from m_user where id = 1 and is_delete = false