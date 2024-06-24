-- Insert into m_biodata
INSERT INTO m_biodata (firstname, lastname, mobile_phone, image_path, created_by, created_on)
VALUES
('John', 'Doe', '082134237421', null, 1, NOW()), -- admin
('Jane', 'Smith', '081234567890', '/images/profile/cat.jpg', 1, NOW()),
('Michael', 'Johnson', '085678912345', '/images/profile/profileeee.jpg', 1, NOW()),
('Emily', 'Brown', '087654321098', null,1 , NOW()),
('Daniel', 'Lee', '089876543210', null, 1, NOW());

-- Insert into m_user
INSERT INTO m_user (biodata_id, role_id, email, password, login_attempt, is_locked, last_login, created_by, created_on)
VALUES
(1, 1, 'john@gmail.com', 'John123123@', 0, false, NULL, 1, NOW()), -- admin
(2, 2, 'jane@gmail.com', 'Jane123123@', 0, false, NULL, 1, NOW()),
(3, 2, 'michael@gmail.com', 'Michael123123@', 0, false, NULL, 1, NOW()),
(4, 2, 'emily@gmail.com', 'Emily123123@', 0, false, NULL, 1, NOW()),
(5, 2, 'daniel@gmail.com', 'Daniel123123@', 0, false, NULL, 1, NOW());

INSERT INTO m_role (name, code, created_by, created_on)
VALUES
('Admin', 'admin', 1, now()),
('Customer', 'customer', 1, now());

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

-- Insert data dummy untuk produk perhiasan
INSERT INTO t_product (category_id, name, description, real_price, discount_rate, discount_price, stock, active, created_by, created_on) VALUES
(1, 'Cincin Berlian Emas Kuning', 'Cincin berlian dengan desain klasik, terbuat dari emas kuning 18 karat.', 450000, 50, 50000 ,10, TRUE, 1, NOW()),
(2, 'Kalung Berlian Safir', 'Kalung berlian safir dengan tali rantai emas putih 18 karat.', 1200000, 50, 45000, 5, TRUE, 1, NOW()),
(3, 'Gelang Perak Etnik', 'Gelang perak dengan desain etnik dan motif tradisional.', 100000, 50, 40000, 15, TRUE, 1, NOW()),
(4, 'Anting-anting Mutiara Elegan', 'Anting-anting mutiara dengan desain elegan dan timeless.', 250000, 50, 60000, 8, TRUE, 1, NOW()),
(5, 'Liontin Berlian Berlian', 'Liontin berlian dengan desain modern dan berlian yang berkilauan.', 550000, 50, 150000, 12, TRUE, 1, NOW());