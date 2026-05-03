-- Roles: admin or customer (defined first; referenced by users)
CREATE TABLE roles (
    role_id   INT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- Users: stores registered trekkers and admins
-- role_id directly here since each user has exactly one role
CREATE TABLE users (
    user_id       INT PRIMARY KEY,
    role_id       INT          NOT NULL DEFAULT 2,   -- 1 = admin, 2 = customer
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,              -- bcrypt/SHA-256 hashed password
    phone         VARCHAR(20),
    profile_image VARCHAR(255),
    is_approved   TINYINT(1)   DEFAULT 0,             -- admin must approve new registrations
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- Trekking packages managed by admin via CRUD dashboard
CREATE TABLE treks (
    trek_id        INT PRIMARY KEY,
    trek_name      VARCHAR(100)                          NOT NULL,
    difficulty     ENUM('Easy','Moderate','Hard','Extreme') NOT NULL,
    duration_days  INT                                   NOT NULL,
    price          DECIMAL(10,2)                         NOT NULL,
    max_group_size INT           DEFAULT 20,
    region         VARCHAR(100),                         -- e.g. Everest, Annapurna, Langtang
    start_location VARCHAR(100),
    end_location   VARCHAR(100),
    distance_km    DECIMAL(6,2),
    image_url      VARCHAR(255),
    description    TEXT,
    is_active      TINYINT(1)    DEFAULT 1              -- admin can deactivate a trek
);

-- Waypoints / detailed route info per trek
CREATE TABLE routes (
    route_id       INT PRIMARY KEY,
    trek_id        INT NOT NULL,
    waypoint_order INT NOT NULL,                        -- sequence of stops along the route
    waypoint_name  VARCHAR(100),
    altitude_m     INT,                                 -- altitude in metres at this waypoint
    notes          TEXT,
    FOREIGN KEY (trek_id) REFERENCES treks(trek_id) ON DELETE CASCADE
);

-- Booking requests submitted by users (approved/rejected by admin)
CREATE TABLE bookings (
    booking_id       INT PRIMARY KEY,
    user_id          INT  NOT NULL,
    trek_id          INT  NOT NULL,
    booking_date     DATE NOT NULL,
    trek_start_date  DATE,
    num_persons      INT  DEFAULT 1,
    special_requests TEXT,
    status           ENUM('pending','confirmed','rejected','cancelled') DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (trek_id) REFERENCES treks(trek_id)
);

-- Payments linked to confirmed bookings
CREATE TABLE payments (
    payment_id      INT PRIMARY KEY,
    booking_id      INT           NOT NULL,
    amount          DECIMAL(10,2) NOT NULL,
    payment_method  ENUM('credit_card','debit_card','paypal','bank_transfer','cash'),
    payment_status  ENUM('pending','paid','refunded','failed') DEFAULT 'pending',
    payment_date    DATE,
    transaction_ref VARCHAR(100),                       -- reference number from payment gateway
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

-- Reviews posted by users who have completed a trek
CREATE TABLE reviews (
    review_id   INT PRIMARY KEY,
    user_id     INT NOT NULL,
    trek_id     INT NOT NULL,
    booking_id  INT,                                    -- ties review to a specific completed booking
    rating      INT CHECK (rating BETWEEN 1 AND 5),
    comment     TEXT,
    review_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (user_id)    REFERENCES users(user_id),
    FOREIGN KEY (trek_id)    REFERENCES treks(trek_id),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);