-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2026 at 08:35 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recording_app_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `albums`
--

CREATE TABLE `albums` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `year` year(4) NOT NULL,
  `artist_id` int(11) NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `albums`
--

INSERT INTO `albums` (`id`, `name`, `year`, `artist_id`, `is_archived`) VALUES
(1, 'My World 2.0', '2010', 1, 0),
(2, 'Evermore', '2020', 2, 0),
(5, 'Believe', '2012', 1, 0),
(6, 'Purpose', '2015', 1, 0),
(7, 'Justice', '2021', 1, 0),
(8, 'Fearless', '2008', 2, 0),
(9, 'Red', '2012', 2, 0),
(10, '1989', '2014', 2, 0),
(11, 'Lover', '2019', 2, 0),
(12, 'Plus', '2011', 3, 0),
(13, 'Multiply', '2014', 3, 0),
(14, 'Divide', '2017', 3, 0),
(15, 'Equals', '2021', 3, 0),
(16, 'Yours Truly', '2013', 5, 0),
(17, 'My Everything', '2014', 5, 0),
(18, 'Dangerous Woman', '2016', 5, 0),
(19, 'Positions', '2020', 5, 0),
(20, 'Doo-Wops & Hooligans', '2010', 5, 1),
(21, 'Unorthodox Jukebox', '2012', 4, 0),
(22, '24K Magic', '2016', 4, 0),
(23, 'maharot', '2026', 19, 0),
(24, 'Rapstar', '2022', 17, 0);

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`id`, `name`, `is_archived`) VALUES
(1, 'Justin Bieber', 0),
(2, 'Taylor Swift', 0),
(3, 'Ed Sheeran', 0),
(4, 'Bruno Mars', 0),
(5, 'Ariana Grande', 0),
(17, 'Flow G', 0),
(19, 'Kamikazee', 0);

-- --------------------------------------------------------

--
-- Table structure for table `playlists`
--

CREATE TABLE `playlists` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_archived` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlists`
--



-- --------------------------------------------------------

--
-- Table structure for table `playlist_songs`
--

CREATE TABLE `playlist_songs` (
  `playlist_id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `is_archived` tinyint(1) DEFAULT 0,
  `length` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`id`, `album_id`, `title`, `genre`, `is_archived`, `length`) VALUES
(1, 1, 'Baby', 'Teen Pop', 0, '00:03:35'),
(2, 1, 'Somebody to Love', 'Dance Pop', 0, '00:03:40'),
(3, 1, 'U Smile', 'Pop', 0, '00:03:17'),
(4, 6, 'Blank Space', 'Pop', 0, '00:03:51'),
(6, 6, 'Alipin', 'pop', 0, '03:15:00'),
(7, 7, 'Shape of You', 'Pop', 0, '00:03:53'),
(8, 7, 'Perfect', 'Pop', 0, '00:04:23'),
(9, 7, 'Galway Girl', 'Folk Pop', 0, '00:02:50'),
(10, 8, 'Into You', 'Pop', 0, '00:04:04'),
(11, 8, 'Dangerous Woman', 'Pop', 0, '00:03:56'),
(12, 8, 'Side to Side', 'Pop', 0, '00:03:46'),
(13, 9, '24K Magic', 'Funk Pop', 0, '00:03:46'),
(14, 9, 'Versace on the Floor', 'R&B', 0, '00:04:21'),
(15, 9, 'That\'s What I Like', 'R&B', 0, '00:03:26'),
(16, 1, '1096 luv', 'pop', 0, '00:05:02'),
(18, 1, 'Perfect', 'Pop', 1, '03:45:00'),
(20, 1, 'Perfect', 'Pop', 1, '03:45:00'),
(22, 1, 'Perfect', 'Pop', 0, '03:45:00'),
(26, 1, 'Perfect', 'Pop', 1, '03:45:00'),
(28, 5, 'Mang Jose', 'Rock', 1, '04:15:00'),
(29, 21, 'inuman', 'opm', 0, '00:00:04'),
(30, 22, 'halik', 'pop', 0, '00:04:15'),
(31, 1, 'sulitin', 'pop', 0, '00:04:18');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'User',
  `is_archived` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--



--
-- Indexes for dumped tables
--

--
-- Indexes for table `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_album` (`name`,`artist_id`),
  ADD KEY `fk_album_artist` (`artist_id`) USING BTREE;

--
-- Indexes for table `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_artist` (`name`);

--
-- Indexes for table `playlists`
--
ALTER TABLE `playlists`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_playlist_user` (`user_id`);

--
-- Indexes for table `playlist_songs`
--
ALTER TABLE `playlist_songs`
  ADD PRIMARY KEY (`playlist_id`,`song_id`),
  ADD UNIQUE KEY `unique_playlist_song` (`playlist_id`,`song_id`),
  ADD KEY `song_id` (`song_id`);

--
-- Indexes for table `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_song_album` (`album_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `albums`
--
ALTER TABLE `albums`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `artists`
--
ALTER TABLE `artists`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `playlists`
--
ALTER TABLE `playlists`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `albums`
--
ALTER TABLE `albums`
  ADD CONSTRAINT `fk_albun_artist` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `playlists`
--
ALTER TABLE `playlists`
  ADD CONSTRAINT `fk_playlist_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `playlist_songs`
--
ALTER TABLE `playlist_songs`
  ADD CONSTRAINT `playlist_songs_ibfk_1` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `playlist_songs_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `fk_song_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
