-- Insert into m_biodata
INSERT INTO m_biodata (fullname, mobile_phone, image_path, created_by, created_on)
VALUES
('John Doe', '082134237421', null, 1, NOW()), -- admin
('Jane Doe', '081234567890', '/images/profile/cat.jpg', 1, NOW());

-- Insert into m_user
INSERT INTO m_user (biodata_id, role_id, email, password, login_attempt, is_locked, last_login, created_by, created_on)
VALUES
(1, 1, 'johndoe@gmail.com', '$2a$12$2TsqAJ.qEvfG7fQh7Be/luWEaW8Nw5ODwjvGbkoEeUlxanHDlUUBO', 0, false, NOW(), 1, NOW()), -- admin --Johndoe123123@
(2, 2, 'janedoe@gmail.com', '$2a$12$xlwIN.8wVWpceBn4DH.ukO16d1Y.x6nyQHp1kiaX7Hk8tjIYzV5w6', 0, false, NOW(), 1, NOW()); --Janedoe123123@

INSERT INTO m_role (name, code, created_by, created_on)
VALUES
('Admin', 'ADMIN', 1, now()),
('User', 'USER', 1, now());

-- INSERT INTO t_token (email, user_id, token, expired_on, is_expired_ used_for, created_by, created_on)
-- VALUES
-- ('john@gmail.com', 1, '123456', '2024-04-05', null, 'OTP', 1, NOW());

-- Insert data dummy untuk kategori perhiasan
--INSERT INTO t_category (name, active, created_by, created_on) VALUES
--('Cincin', TRUE, 1, NOW()),
--('Kalung', TRUE, 1, NOW()),
--('Gelang', TRUE, 1, NOW()),
--('Anting-anting', TRUE, 1, NOW()),
--('Liontin', TRUE, 1, NOW()),
--('Bros', TRUE, 1, NOW());

--INSERT INTO t_product (category_id, name, image_path, price, description, active, created_by, created_on) VALUES
--(1, 'Cincin Berlian Emas Kuning', null, 10000, 'Cincin berlian dengan desain klasik, terbuat dari emas kuning 18 karat.', TRUE, 1, NOW()),
--(1, 'Cincin Berlian Emas Putih', null, 15000, 'Cincin berlian dengan desain klasik, terbuat dari emas putih 18 karat.', TRUE, 1, NOW()),
--(2, 'Kalung Berlian Safir', null, 20000, 'Kalung berlian safir dengan tali rantai emas putih 18 karat.', TRUE, 1, NOW()),
--(3, 'Gelang Perak Etnik', null, 30000, 'Gelang perak dengan desain etnik dan motif tradisional.', TRUE, 1, NOW()),
--(4, 'Anting-anting Mutiara', null, 50000, 'Anting-anting mutiara dengan desain klasik dan timeless.', TRUE, 1, NOW()),
--(4, 'Anting-anting Mutiara Elegan', null, 5000, 'Anting-anting mutiara dengan desain elegan dan timeless.', TRUE, 1, NOW()),
--(5, 'Liontin Berlian Berlian', null, 14000, 'Liontin berlian dengan desain modern dan berlian yang berkilauan.', TRUE, 1, NOW());

-- T_CATEGORY FOR PAGING
INSERT INTO t_category (name, active, created_by, created_on) VALUES
('Kalung Mutiara', TRUE, 1, NOW()),
('Cincin Kawin', TRUE, 1, NOW()),
('Gelang Berlian', TRUE, 1, NOW()),
('Anting Zircon', TRUE, 1, NOW()),
('Kalung Emas Putih', TRUE, 1, NOW()),
('Gelang Charm', TRUE, 1, NOW()),
('Kalung Safir', TRUE, 1, NOW()),
('Cincin Tungsten', TRUE, 1, NOW()),
('Gelang Emas Kuning', TRUE, 1, NOW()),
('Anting Emas Kuning', TRUE, 1, NOW()),
('Kalung Rantai Emas', TRUE, 1, NOW()),
('Cincin Perak', TRUE, 1, NOW()),
('Bros Berlian', TRUE, 1, NOW()),
('Cincin Titanium', TRUE, 1, NOW()),
('Anting Zircon Elegan', TRUE, 1, NOW()),
('Liontin Emas Putih', TRUE, 1, NOW()),
('Kalung Berlian Klasik', TRUE, 1, NOW()),
('Gelang Berlian Mewah', TRUE, 1, NOW()),
('Anting Berlian Kecil', TRUE, 1, NOW()),
('Liontin Mutiara', TRUE, 1, NOW());

INSERT INTO t_product (category_id, name, image_path, price, description, active, created_by, created_on) VALUES
(7, 'Kalung Safir Biru', null, 25000, 'Kalung dengan batu safir biru.', TRUE, 1, NOW()),
(2, 'Cincin Kawin Platinum', null, 18000, 'Cincin kawin platinum.', TRUE, 1, NOW()),
(3, 'Gelang Berlian Modern', null, 30000, 'Gelang berlian dengan desain modern.', TRUE, 1, NOW()),
(1, 'Kalung Mutiara Klasik', null, 12000, 'Kalung mutiara dengan desain klasik.', TRUE, 1, NOW()),
(4, 'Anting Zircon Mewah', null, 6000, 'Anting zircon dengan desain mewah.', TRUE, 1, NOW()),
(5, 'Kalung Emas Putih Rantai', null, 24000, 'Kalung emas putih rantai.', TRUE, 1, NOW()),
(6, 'Gelang Charm Trendy', null, 9000, 'Gelang charm trendy.', TRUE, 1, NOW()),
(8, 'Cincin Tungsten Polos', null, 11000, 'Cincin tungsten polos.', TRUE, 1, NOW()),
(9, 'Gelang Emas Kuning Sederhana', null, 15000, 'Gelang emas kuning sederhana.', TRUE, 1, NOW()),
(10, 'Anting Emas Kuning Mewah', null, 13000, 'Anting emas kuning mewah.', TRUE, 1, NOW()),
(11, 'Kalung Rantai Emas Tipis', null, 21000, 'Kalung rantai emas tipis.', TRUE, 1, NOW()),
(12, 'Cincin Perak Mewah', null, 7000, 'Cincin perak dengan desain mewah.', TRUE, 1, NOW()),
(13, 'Bros Berlian Sederhana', null, 9000, 'Bros berlian sederhana.', TRUE, 1, NOW()),
(14, 'Cincin Titanium Kokoh', null, 17000, 'Cincin titanium kokoh.', TRUE, 1, NOW()),
(15, 'Anting Zircon Elegan Kecil', null, 8000, 'Anting zircon elegan kecil.', TRUE, 1, NOW()),
(16, 'Liontin Emas Putih Bulat', null, 14000, 'Liontin emas putih bulat.', TRUE, 1, NOW()),
(17, 'Kalung Berlian Klasik Elegan', null, 30000, 'Kalung berlian klasik elegan.', TRUE, 1, NOW()),
(18, 'Gelang Berlian Mewah Sederhana', null, 35000, 'Gelang berlian mewah sederhana.', TRUE, 1, NOW()),
(19, 'Anting Berlian Kecil Cantik', null, 4000, 'Anting berlian kecil cantik.', TRUE, 1, NOW()),
(20, 'Liontin Mutiara Klasik', null, 10000, 'Liontin mutiara klasik.', TRUE, 1, NOW());



INSERT INTO t_product_size (product_id, size_name, stock, active, created_by, created_on) VALUES
(1, 'S', 10, TRUE, 1, NOW()),
(1, 'M', 20, TRUE, 1, NOW()),
(1, 'L', 30, TRUE, 1, NOW()),
(1, 'XL', 40, TRUE, 1, NOW()),
(2, 'S', 10, TRUE, 1, NOW()),
(2, 'M', 20, TRUE, 1, NOW()),
(3, 'L', 30, TRUE, 1, NOW()),
(3, 'XL', 40, TRUE, 1, NOW());


-- Insert data dummy for cart
--INSERT INTO t_cart(product_id, user_id, product_size_id, quantity, total_price, created_by, created_on) VALUES
--(1, 1, 1, 5, 0, 1, NOW()),
--(1, 1, 2, 3, 0, 1, NOW()),
--(2, 2, 2, 6, 0, 1, NOW()),
--(3, 1, 3, 7, 0, 1, NOW()),
--(4, 2, 4, 8, 0, 1, NOW()),
--(5, 2, 4, 9, 0, 1, NOW());