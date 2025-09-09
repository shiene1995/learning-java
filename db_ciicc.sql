-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2025 at 06:04 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ciicc`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_account`
--

CREATE TABLE `tb_account` (
  `id` int(11) NOT NULL,
  `Fname` text NOT NULL,
  `Lname` text NOT NULL,
  `Username` text NOT NULL,
  `PasswordHash` text NOT NULL,
  `SaltHash` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_account`
--

INSERT INTO `tb_account` (`id`, `Fname`, `Lname`, `Username`, `PasswordHash`, `SaltHash`) VALUES
(1, 'Kenneth', 'Salvador', 'admin', 'zvtTVcPcBi6bUNJC8yaMKKFcuNufaPN1UF4ur2rixrk=', 'rQ4ap4rJH2aocu2XuJrMUw=='),
(2, 'Sam', 'Salvador', 'sam', 'Gqy92fysQ/dmDtnmHnDj9te5kPT5hWiuga7ZPFWKTkM=', 'Y+zeb9+HhNmRBF/dsRTNTw=='),
(3, 'Jalyn', 'Salvador', 'jalyn', 'NQwQ4vIo3nS8c8CiGRWhXQ61j/mLqTdu77tUTvwoKCo=', 'fofW+6a0H5YtmurntocHAg=='),
(4, 'Zyrinne', 'Salvador', 'zyrinne', 'hrl1r6qRFn8wXQ3URjQNCDV4iwtmnWltpF4AkPv6lpE=', 'IpMBFOe94XCjcjJQ10iZGw==');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_account`
--
ALTER TABLE `tb_account`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_account`
--
ALTER TABLE `tb_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
