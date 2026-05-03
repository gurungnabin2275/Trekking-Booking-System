-- Roles (inserted first; users.role_id references this table)
INSERT INTO roles VALUES
(1, 'admin'),
(2, 'customer');

-- Users (role_id column replaces the old user_roles junction table)
-- Passwords are bcrypt-hashed placeholders; plaintext: admin123 / user123
INSERT INTO users (user_id, role_id, name, email, password_hash, phone, is_approved) VALUES
(1, 1, 'Admin User',  'admin@trekhub.com', '$2a$12$adminHashedPasswordXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '9800000001', 1),
(2, 2, 'Ram Sharma',  'ram@example.com',   '$2a$12$ramHashedPasswordXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '9800000002', 1),
(3, 2, 'Sita Rai',    'sita@example.com',  '$2a$12$sitaHashedPasswordXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '9811111111', 1),
(4, 2, 'Hari Thapa',  'hari@example.com',  '$2a$12$hariHashedPasswordXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '9822222222', 0); -- pending approval

-- Trekking packages
INSERT INTO treks (trek_id, trek_name, difficulty, duration_days, price, max_group_size, region, start_location, end_location, distance_km, image_url, description, is_active) VALUES
(1, 'Everest Base Camp',   'Hard',     14, 1200.00, 15, 'Everest',   'Lukla',      'Everest Base Camp', 130.50, '/images/ebc.jpg',       'The world-famous trek to the foot of Mt Everest passing through Sherpa villages and high-altitude terrain.', 1),
(2, 'Annapurna Circuit',   'Moderate', 12,  900.00, 20, 'Annapurna', 'Besisahar',  'Nayapul',           160.00, '/images/annapurna.jpg', 'A classic circuit around the Annapurna massif with diverse landscapes from subtropical forest to high alpine desert.', 1),
(3, 'Langtang Valley',     'Moderate',  7,  650.00, 18, 'Langtang',  'Syabrubesi', 'Kyanjin Gompa',      65.00, '/images/langtang.jpg',  'A shorter yet stunning trek through the Langtang Valley with views of glaciers and traditional Tamang villages.', 1),
(4, 'Ghorepani Poon Hill', 'Easy',      5,  400.00, 25, 'Annapurna', 'Nayapul',    'Birethanti',         43.00, '/images/poonhill.jpg',  'Perfect for beginners — spectacular sunrise panorama over the Annapurna and Dhaulagiri ranges.', 1);

-- Route waypoints
INSERT INTO routes (route_id, trek_id, waypoint_order, waypoint_name, altitude_m, notes) VALUES
(1,  1, 1, 'Lukla',             2860, 'Flight from Kathmandu; trek starts here'),
(2,  1, 2, 'Namche Bazaar',     3440, 'Main Sherpa town; acclimatisation stop'),
(3,  1, 3, 'Tengboche',         3867, 'Famous monastery with Everest views'),
(4,  1, 4, 'Dingboche',         4410, 'Second acclimatisation day recommended'),
(5,  1, 5, 'Everest Base Camp', 5364, 'Trek high point; Khumbu Glacier'),
(6,  2, 1, 'Besisahar',          760, 'Road-head; trek starts here'),
(7,  2, 2, 'Manang',            3519, 'Rest day for acclimatisation'),
(8,  2, 3, 'Thorong La Pass',   5416, 'Highest point of the circuit'),
(9,  2, 4, 'Muktinath',         3760, 'Sacred temple; end of high-altitude section'),
(10, 2, 5, 'Nayapul',           1070, 'Trek end; bus back to Pokhara'),
(11, 3, 1, 'Syabrubesi',        1550, 'Trailhead; permit checkpoint'),
(12, 3, 2, 'Langtang Village',  3430, 'Rebuilt after 2015 earthquake'),
(13, 3, 3, 'Kyanjin Gompa',     3870, 'Monastery and cheese factory; optional Kyanjin Ri climb'),
(14, 4, 1, 'Nayapul',           1070, 'Drive from Pokhara'),
(15, 4, 2, 'Ghorepani',         2860, 'Tea-house hub; Rhododendron forests'),
(16, 4, 3, 'Poon Hill',         3210, 'Sunrise viewpoint — 4:30 AM start'),
(17, 4, 4, 'Birethanti',        1025, 'Trek ends; return to Pokhara');

-- Bookings
INSERT INTO bookings (booking_id, user_id, trek_id, booking_date, trek_start_date, num_persons, special_requests, status) VALUES
(1, 2, 1, '2026-04-20', '2026-05-01', 2, 'Vegetarian meals preferred', 'confirmed'),
(2, 3, 2, '2026-05-10', '2026-06-15', 1, NULL,                          'pending'),
(3, 2, 3, '2026-05-01', '2026-05-20', 3, 'Need porter for heavy bags',  'confirmed'),
(4, 4, 4, '2026-05-02', '2026-05-25', 1, NULL,                          'pending');  -- Hari not yet approved

-- Payments
INSERT INTO payments (payment_id, booking_id, amount, payment_method, payment_status, payment_date, transaction_ref) VALUES
(1, 1, 2400.00, 'credit_card',   'paid',    '2026-04-20', 'TXN-2026-0420-001'),  -- 2 persons × 1200
(2, 2,  900.00, 'paypal',        'pending', '2026-05-10', 'TXN-2026-0510-002'),
(3, 3, 1950.00, 'bank_transfer', 'paid',    '2026-05-01', 'TXN-2026-0501-003'),  -- 3 persons × 650
(4, 4,  400.00, 'credit_card',   'pending', '2026-05-02', 'TXN-2026-0502-004');

-- Reviews (tied to verified completed bookings)
INSERT INTO reviews (review_id, user_id, trek_id, booking_id, rating, comment, review_date) VALUES
(1, 2, 1, 1, 5, 'Life-changing experience. The views from Base Camp are beyond words. Guides were professional and safety-focused.', '2026-05-16'),
(2, 2, 3, 3, 4, 'Langtang is underrated! Fewer crowds than EBC and the Kyanjin Gompa sunrise was stunning. Porter service excellent.', '2026-06-04');