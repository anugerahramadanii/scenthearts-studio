-- Insert into m_biodata
INSERT INTO m_biodata (firstname, lastname, mobile_phone, created_by, created_on)
VALUES
('John', 'Doe', '082134237421', 1, NOW()), -- admin
('Jane', 'Smith', '081234567890', 1, NOW()),
('Michael', 'Johnson', '085678912345', 1, NOW()),
('Emily', 'Brown', '087654321098', 1, NOW()),
('Daniel', 'Lee', '089876543210', 1, NOW());

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
('Buyer', 'buyer', 1, now());

-- INSERT INTO t_token (email, user_id, token, expired_on, is_expired_ used_for, created_by, created_on)
-- VALUES
-- ('john@gmail.com', 1, '123456', '2024-04-05', null, 'OTP', 1, NOW());

-- Insert data dummy untuk kategori perhiasan
INSERT INTO t_category (initial, name, active, created_by, created_on) VALUES
('CN', 'Cincin', TRUE, 1, NOW()),
('KL', 'Kalung', TRUE, 1, NOW()),
('GL', 'Gelang', TRUE, 1, NOW()),
('AA', 'Anting-anting', TRUE, 1, NOW()),
('LL', 'Liontin', TRUE, 1, NOW()),
('BR', 'Bros', TRUE, 1, NOW());
-- Insert data dummy untuk produk perhiasan
INSERT INTO t_product (category_id, initial, name, description, price, stock, active, created_by, created_on) VALUES
(1, 'JWL01P01', 'Cincin Berlian Emas Putih', 'Cincin berlian dengan desain elegan, terbuat dari emas putih 18 karat.', 50000, 10, TRUE, 1, NOW()),
(2, 'JWL02P01', 'Kalung Berlian Mutiara', 'Kalung berlian mutiara dengan tali rantai perak sterling.', 800000, 5, TRUE, 1, NOW()),
(3, 'JWL03P01', 'Gelang Perak Modern', 'Gelang perak dengan desain modern dan elegan.', 150000, 15, TRUE, 1, NOW()),
(4, 'JWL04P01', 'Anting-anting Berlian Mewah', 'Anting-anting berlian dengan desain mewah dan berkilauan.', 350000, 8, TRUE, 1, NOW()),
(5, 'JWL05P01', 'Liontin Berlian 18 Karat', 'Liontin berlian dengan emas 18 karat dan desain klasik.', 450000, 12, TRUE, 1, NOW()),
(6, 'JWL06P01', 'Bros Bunga Mawar', 'Bros bunga mawar dengan bahan perak sterling dan desain yang elegan.', 200000, 20, TRUE, 1, NOW());