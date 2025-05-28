
--
-- Database: `hotel`
--
--
-- Table structure for table `guest`
--

CREATE TABLE guest (
  room_ID INTEGER NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  Address VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  Nationality VARCHAR(50) NOT NULL,
  passport_Number VARCHAR(50) PRIMARY KEY,
  phoneNo VARCHAR(50) NOT NULL,
  Card_Number VARCHAR(50) NOT NULL,
  card_Pass VARCHAR(50) NOT NULL,
  number_Of_Days INTEGER NOT NULL,
  fees DOUBLE PRECISION NOT NULL
);

--
-- Dumping data for table `guest`
--

INSERT INTO guest (room_ID, Name, Email, Address, city, Nationality, passport_Number, phoneNo, Card_Number, card_Pass, number_Of_Days, fees) VALUES
(72, 'Eslam', 'jgfyi', 'ggffyf', 'gfghf', 'fyy', '012055', '0254545', '54541', '5454', 26, 0),
(81, 'Mohamed', 'Mohamed@yahoo.com', 'masr el gedida', 'Cairo', 'Egyption', '8987-07321-3213', '01020781942', '76676786980989', '65456', 6, 0),
(71, 'Abdelrahman', 'abdo@gmail.com', 'mustafa Kamel', 'alex', 'egypt', '97987987897', '9080980989080', '8978978978', '9879798798', 6, 0),
(2, 'gyhggvb', 'ggygvgfbgyg', 'hgjhg', 'yggug', 'ggggg', 'gfvgdfgg', 'ggg', '8797595', '562779852', 16, 0),
(1, 'hh', 'hh', 'h h', 'h', 'h', 'h', 'h', 'h', 'h', 10601, 0),
(3, 'kjl', 'dasa@hjh.com', '78678', 'ihgjhjk', 'hjkh', 'jhkjhjkhkj', '6786876', '787', '87787', 22, 4400);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE room (
  roomID INTEGER NOT NULL,
  room_Type VARCHAR(15) NOT NULL,
  room_capacity VARCHAR(15) NOT NULL,
  Check_In_Date DATE NOT NULL,
  Check_Out_Date DATE NOT NULL,
  isEmpty BOOLEAN NOT NULL
);

--
-- Dumping data for table `room`
--

INSERT INTO room (roomID, room_Type, room_capacity, Check_In_Date, Check_Out_Date, isEmpty) VALUES
(1, 'Economy', 'Single', '2019-05-12', '2048-05-20', FALSE),
(2, 'Economy', 'Single', '2019-08-13', '2019-08-29', FALSE),
(3, 'Economy', 'Single', '2020-06-08', '2020-06-30', FALSE),
(4, 'Economy', 'Single', '2019-04-14', '2019-04-14', TRUE),
(5, 'Economy', 'Single', '2019-04-14', '2019-04-14', TRUE),
(6, 'Economy', 'Single', '2019-04-14', '2019-04-14', TRUE),
(7, 'Economy', 'Single', '2019-04-14', '2019-04-14', TRUE),
(8, 'Economy', 'Single', '2019-04-14', '2019-04-19', TRUE),
(9, 'Economy', 'Single', '2019-04-14', '2019-04-22', TRUE),
(10, 'Economy', 'Single', '2019-04-14', '2019-04-22', TRUE),
(11, 'Economy', 'Double', '2019-04-23', '2019-04-15', TRUE),
(12, 'Economy', 'Double', '2019-04-14', '2019-04-18', TRUE),
(13, 'Economy', 'Double', '2019-04-14', '2019-04-14', TRUE),
(14, 'Economy', 'Double', '2019-04-14', '2019-04-15', TRUE),
(15, 'Economy', 'Double', '2019-04-14', '2019-04-14', TRUE),
(16, 'Economy', 'Double', '2019-04-14', '2019-04-16', TRUE),
(17, 'Economy', 'Double', '2019-04-14', '2019-04-14', TRUE),
(18, 'Economy', 'Double', '2019-04-14', '2019-04-18', TRUE),
(19, 'Economy', 'Double', '2019-04-14', '2019-04-15', TRUE),
(20, 'Economy', 'Double', '2019-04-14', '2019-04-18', TRUE),
(21, 'Economy', 'Triple', '2019-04-16', '2019-04-21', TRUE),
(22, 'Economy', 'Triple', '2019-04-16', '2019-04-21', TRUE),
(23, 'Economy', 'Triple', '2019-04-18', '2019-04-18', TRUE),
(24, 'Economy', 'Triple', '2019-04-15', '2019-04-15', TRUE),
(25, 'Economy', 'Triple', '2019-04-14', '2019-04-15', TRUE),
(26, 'Economy', 'Triple', '2019-04-14', '2019-04-15', TRUE),
(27, 'Economy', 'Triple', '2019-04-14', '2019-04-16', TRUE),
(28, 'Economy', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(29, 'Economy', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(30, 'Economy', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(31, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(32, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(33, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(34, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(35, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(36, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(37, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(38, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(39, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(40, 'Normal', 'Single', '2019-04-14', '2019-04-14', TRUE),
(41, 'Normal', 'Double', '2019-04-28', '2019-04-22', TRUE),
(42, 'Normal', 'Double', '2019-04-23', '2019-04-22', TRUE),
(43, 'Normal', 'Double', '2019-04-14', '2019-04-14', TRUE),
(44, 'Normal', 'Double', '2019-04-18', '2019-04-21', TRUE),
(45, 'Normal', 'Double', '2019-04-14', '2019-04-21', TRUE),
(46, 'Normal', 'Double', '2019-04-14', '2019-04-14', TRUE),
(47, 'Normal', 'Double', '2019-04-14', '2019-04-14', TRUE),
(48, 'Normal', 'Double', '2019-04-14', '2019-04-16', TRUE),
(49, 'Normal', 'Double', '2019-04-14', '2019-04-14', TRUE),
(50, 'Normal', 'Double', '2019-04-14', '2019-04-15', TRUE),
(51, 'Normal', 'Triple', '2019-04-23', '2019-04-15', TRUE),
(52, 'Normal', 'Triple', '2019-04-30', '2019-04-30', TRUE),
(53, 'Normal', 'Triple', '2019-04-17', '2019-04-30', TRUE),
(54, 'Normal', 'Triple', '2019-04-14', '2019-05-04', TRUE),
(55, 'Normal', 'Triple', '2019-04-14', '2019-04-18', TRUE),
(56, 'Normal', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(57, 'Normal', 'Triple', '2019-04-14', '2019-04-16', TRUE),
(58, 'Normal', 'Triple', '2019-04-14', '2019-04-16', TRUE),
(59, 'Normal', 'Triple', '2019-04-14', '2019-04-16', TRUE),
(60, 'Normal', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(61, 'Vip', 'Single', '2019-05-27', '2019-05-04', TRUE),
(62, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(63, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(64, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(65, 'Vip', 'Single', '2019-04-14', '2019-04-18', TRUE),
(66, 'Vip', 'Single', '2019-04-15', '2019-04-16', TRUE),
(67, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(68, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(69, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(70, 'Vip', 'Single', '2019-04-14', '2019-04-14', TRUE),
(71, 'Vip', 'Double', '2019-04-24', '2019-04-30', FALSE),
(72, 'Vip', 'Double', '2019-05-04', '2019-05-30', FALSE),
(73, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(74, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(75, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(76, 'Vip', 'Double', '2019-04-14', '2019-04-22', TRUE),
(77, 'Vip', 'Double', '2019-04-01', '2019-04-21', TRUE),
(78, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(79, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(80, 'Vip', 'Double', '2019-04-14', '2019-04-14', TRUE),
(81, 'Vip', 'Triple', '2019-05-16', '2019-05-22', FALSE),
(82, 'Vip', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(83, 'Vip', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(84, 'Vip', 'Triple', '2019-04-14', '2019-04-14', TRUE),
(85, 'Vip', 'Triple', '2019-04-19', '2019-04-21', TRUE),
(86, 'Vip', 'Triple', '2019-04-18', '2019-04-19', TRUE),
(87, 'Vip', 'Triple', '2019-04-16', '2019-04-19', TRUE),
(88, 'Vip', 'Triple', '2019-04-14', '2019-04-19', TRUE),
(89, 'Vip', 'Triple', '2019-04-14', '2019-04-19', TRUE),
(90, 'Vip', 'Triple', '2019-04-14', '2019-04-15', TRUE);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE "user" (
  user_name VARCHAR(50) NOT NULL,
  user_pass INTEGER NOT NULL,
  is_admin BOOLEAN NOT NULL
);
--
-- Dumping data for table `user`
--

INSERT INTO "user" (user_name, user_pass, is_admin) VALUES
('abdo', 1999, TRUE),
('AHMED', 215295, FALSE),
('amgad', 1997, TRUE),
('bayoumi', 1999, TRUE),
('ehab', 1234, FALSE),
('eslam', 1998, TRUE),
('hassan', 1234, FALSE),
('mustafa', 1234, FALSE),
('sabreen', 2000, TRUE),
('youssef', 2000, TRUE);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guest`
--
ALTER TABLE guest
  ADD CONSTRAINT "guest_primary_key" PRIMARY KEY (passport_Number);

--
-- Indexes for table `room`
--
ALTER TABLE room
  ADD CONSTRAINT "room_primary_key" PRIMARY KEY (roomID);

--
-- Indexes for table `user`
--
ALTER TABLE "user"
  ADD CONSTRAINT "user_primary_key" PRIMARY KEY (user_name);

--
-- AUTO_INCREMENT for dumped tables
--

-- Sequence for Auto Increment (PostgreSQL uses sequences, so we need to create one for `roomID` in PostgreSQL)
CREATE SEQUENCE room_roomID_seq
  START WITH 91
  INCREMENT BY 1;

-- Set default value for roomID to use the sequence for auto-increment
ALTER TABLE room
  ALTER COLUMN roomID SET DEFAULT nextval('room_roomID_seq');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
