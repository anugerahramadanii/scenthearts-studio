-- Insert into m_biodata
INSERT INTO m_biodata (firstname, lastname, mobile_phone, image_path, created_by, created_on)
VALUES
('John', 'Doe', '082134237421', null, 1, NOW()), -- admin
('Jane', 'Smith', '081234567890', '/images/profile/cat.jpg', 1, NOW());

-- Insert into m_user
INSERT INTO m_user (biodata_id, role_id, email, password, login_attempt, is_locked, last_login, created_by, created_on)
VALUES
(1, 1, 'johndoe@gmail.com', '$2a$12$2TsqAJ.qEvfG7fQh7Be/luWEaW8Nw5ODwjvGbkoEeUlxanHDlUUBO', 0, false, NULL, 1, NOW()), -- admin --Johndoe123123@
(2, 2, 'janedoe@gmail.com', '$2a$12$xlwIN.8wVWpceBn4DH.ukO16d1Y.x6nyQHp1kiaX7Hk8tjIYzV5w6', 0, false, NULL, 1, NOW()); --Janedoe123123@

INSERT INTO m_role (name, code, created_by, created_on)
VALUES
('Admin', 'admin', 1, now()),
('user', 'user', 1, now());

-- INSERT INTO t_token (email, user_id, token, expired_on, is_expired_ used_for, created_by, created_on)
-- VALUES
-- ('john@gmail.com', 1, '123456', '2024-04-05', null, 'OTP', 1, NOW());

-- Insert data dummy untuk kategori perhiasan
INSERT INTO t_category (name, active, created_by, created_on) VALUES
('Cincin', TRUE, 1, NOW()),
('Kalung', TRUE, 1, NOW()),
('Gelang', TRUE, 1, NOW()),
('Anting-anting', TRUE, 1, NOW()),
('Liontin', TRUE, 1, NOW()),
('Bros', TRUE, 1, NOW());

INSERT INTO t_product (category_id, name, image_path, description, price, active, created_by, created_on) VALUES
(1, 'Cincin Berlian Emas Kuning', null, 'Cincin berlian dengan desain klasik, terbuat dari emas kuning 18 karat.', 10000, TRUE, 1, NOW()),
(1, 'Cincin Berlian Emas Putih', null, 'Cincin berlian dengan desain klasik, terbuat dari emas putih 18 karat.',  15000, TRUE, 1, NOW()),
(2, 'Kalung Berlian Safir', null, 'Kalung berlian safir dengan tali rantai emas putih 18 karat.', 20000, TRUE, 1, NOW()),
(3, 'Gelang Perak Etnik', null, 'Gelang perak dengan desain etnik dan motif tradisional.', 30000, TRUE, 1, NOW()),
(4, 'Anting-anting Mutiara', null, 'Anting-anting mutiara dengan desain klasik dan timeless.', 50000, TRUE, 1, NOW()),
(4, 'Anting-anting Mutiara Elegan', null,'Anting-anting mutiara dengan desain elegan dan timeless.', 5000, TRUE, 1, NOW()),
(5, 'Liontin Berlian Berlian', null, 'Liontin berlian dengan desain modern dan berlian yang berkilauan.', 14000, TRUE, 1, NOW());
--
--
INSERT INTO t_product_size (product_id, name, quantity, active, created_by, created_on) VALUES
(1, 'S', 10, TRUE, 1, NOW()),
(1, 'M', 20, TRUE, 1, NOW()),
(1, 'L', 30, TRUE, 1, NOW()),
(1, 'XL', 40, TRUE, 1, NOW());

-- Insert data dummy for cart
--INSERT INTO t_cart(product_id, user_id, product_size_id, quantity, total_price, created_by, created_on) VALUES
--(1, 1, 1, 5, 0, 1, NOW()),
--(1, 1, 2, 3, 0, 1, NOW()),
--(2, 2, 2, 6, 0, 1, NOW()),
--(3, 1, 3, 7, 0, 1, NOW()),
--(4, 2, 4, 8, 0, 1, NOW()),
--(5, 2, 4, 9, 0, 1, NOW());