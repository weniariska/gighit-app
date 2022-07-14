-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2022 at 06:51 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gighit_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `drink`
--

CREATE TABLE `drink` (
  `namaDrink` varchar(50) NOT NULL,
  `drinkPict` varchar(75) DEFAULT 'images/drink/drink.png',
  `drinkPriceM` double(10,1) NOT NULL,
  `drinkPriceL` double(10,1) NOT NULL,
  `drinkDesc` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drink`
--

INSERT INTO `drink` (`namaDrink`, `drinkPict`, `drinkPriceM`, `drinkPriceL`, `drinkDesc`) VALUES
('Ice Green Tea', 'images/drink/greenTea.png', 10000.0, 13000.0, 'with green tea and fresh milk'),
('Ice Lime Tea', 'images/drink/limeTea.png', 8000.0, 11000.0, 'with fresh lime and tea'),
('Ice Mocca', 'images/drink/iceMocca.png', 9000.0, 14000.0, 'a tasty ice mocca'),
('Ice Oreo', 'images/drink/oreo.png', 13000.0, 17000.0, 'a tasty oreo with fresh pop ice'),
('Ice Ovaltine', 'images/drink/ovaltine.png', 13000.0, 17000.0, 'with sweet ovaltine and fresh milk'),
('Ice Taro', 'images/drink/taro.png', 12000.0, 15000.0, 'with sweet taro and fresh milk');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `kode_pesanan` int(11) NOT NULL,
  `nama_pemesan` varchar(50) NOT NULL,
  `tgl_belanja` date DEFAULT NULL,
  `ttl_belanja` double(10,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pizza`
--

CREATE TABLE `pizza` (
  `namaPizza` varchar(50) NOT NULL,
  `pizzaPict` varchar(75) DEFAULT 'images/pizza/pizza.png',
  `pizzaPriceM` double(10,1) NOT NULL,
  `pizzaPriceL` double(10,1) NOT NULL,
  `pizzaDesc` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pizza`
--

INSERT INTO `pizza` (`namaPizza`, `pizzaPict`, `pizzaPriceM`, `pizzaPriceL`, `pizzaDesc`) VALUES
('Ai Fungi', 'images/pizza/aiFungi.png', 58000.0, 94000.0, 'fresh mushrooms with mozzarella cheese'),
('Bbq Chicken', 'images/pizza/bbqChicken.png', 60000.0, 110000.0, 'chicken decked with red onion and tasty bbq sauce'),
('Capricciosa', 'images/pizza/capricciosa.png', 57000.0, 980000.0, 'tomato sauce topped with ham and atrichoke'),
('Farmhouse', 'images/pizza/farmhousePizza.png', 70000.0, 105000.0, 'fresh mushrooms, tomato sauce, ham, mozzarella cheese'),
('Margherita', 'images/pizza/margheritaPizza.png', 56000.0, 90000.0, 'a tomato sauce base, mozarella, tomato chunks, oregano'),
('Meat Lovers', 'images/pizza/meatLovers.png', 70000.0, 130000.0, 'pepperoni, spicy beed, ham and spicy pork on a tomato sauce base'),
('Pepperoni Lovers', 'images/pizza/pepperoniLoversPizza.png', 65000.0, 115000.0, 'a tomato sauce base topped with mozzarella cheese, chunks and oregano'),
('Supreme', 'images/pizza/supremePizza.png', 75000.0, 120000.0, 'pepperoni, daging sapi, jamur, paprika hijau, mozarella');

-- --------------------------------------------------------

--
-- Table structure for table `starters`
--

CREATE TABLE `starters` (
  `namaStarters` varchar(50) NOT NULL,
  `startersPict` varchar(75) DEFAULT 'images/drink/drink.png',
  `startersPrice` double(10,1) NOT NULL,
  `startersDesc` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `starters`
--

INSERT INTO `starters` (`namaStarters`, `startersPict`, `startersPrice`, `startersDesc`) VALUES
('Bread Sticks', 'images/starters/breadSticks.png', 23000.0, 'tasty bread sticks'),
('Chicken Strips', 'images/starters/chickenStrips.png', 30000.0, 'tasty chicken strips'),
('Chicken Wrap', 'images/starters/chickenWrap.png', 25000.0, 'tortilla with spicy bbq sauce, chicken, fresh mushrooms'),
('Garlic Bread', 'images/starters/garlicBread.png', 17000.0, 'tasty garlic bread'),
('Mozza Bread', 'images/starters/mozzaGarlicBread.png', 21000.0, 'garlic bread with mozzarella cheese'),
('Vegetarian Wrap', 'images/starters/vegetarianWrap.png', 20000.0, 'tortilla with spicy bbq sauce, vegetarian, fresh mushrooms');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `namaPelanggan` varchar(50) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `ttl_belanja` double(10,1) NOT NULL,
  `username` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `namaPelanggan`, `tgl_transaksi`, `ttl_belanja`, `username`) VALUES
(1, 'Aqilah Dev', '2021-01-08', 17000.0, ''),
(2, 'Anita', '2021-01-08', 38000.0, ''),
(3, 'Valerian Putra', '2021-01-08', 20000.0, 'koko'),
(4, 'Valerian Putra', '2021-01-08', 8000.0, 'koko'),
(5, 'Valerian Putra', '2021-01-08', 17000.0, 'koko'),
(6, 'Valerian Putra', '2021-01-08', 10000.0, 'koko'),
(7, 'Wen Ar', '2021-01-08', 42000.0, 'wee'),
(8, 'Naysila', '2021-01-08', 32000.0, 'wee'),
(9, 'Azzahra', '2021-01-08', 40000.0, 'wee'),
(10, 'Alif', '2021-01-08', 90000.0, 'wee'),
(11, 'Wen Ar', '2021-01-08', 17000.0, 'wee'),
(12, 'Ani Sunarni', '2021-01-08', 72000.0, ''),
(13, 'Valerian Putra', '2021-01-08', 60000.0, 'koko'),
(14, 'Nana Irna', '2021-01-16', 23000.0, ''),
(15, 'Kamilah Mumtaz', '2021-01-16', 10000.0, 'wee'),
(16, 'Claudia Okta', '2021-01-16', 48000.0, ''),
(17, 'Alina', '2021-01-24', 110000.0, ''),
(18, 'Budi kalisa', '2021-01-24', 23000.0, 'budbud'),
(19, 'Valerian Putra', '2021-01-25', 60000.0, 'koko');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `namaDepan` varchar(50) NOT NULL,
  `namaBelakang` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `saldo` double(10,1) DEFAULT 0.0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `namaDepan`, `namaBelakang`, `alamat`, `saldo`) VALUES
('budbud', '123', 'Budi', 'kalisa', 'Jalan', 48000.0),
('koko', '321', 'Valerian', 'Putra', 'jalan haji ali', 83000.0),
('kokotuta', '123456', 'Putra', 'Pratama', 'Jalan Kramat Jati', 0.0),
('mikimiki', '222', 'Miki', 'Atita', 'Jalan Cibubur', 0.0),
('rafael_fam', '111', 'Rafael', 'Fam', 'Jalan SD', 0.0),
('wee', '123', 'Wen', 'Ar', 'jalanjln', 90000.0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `drink`
--
ALTER TABLE `drink`
  ADD PRIMARY KEY (`namaDrink`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`kode_pesanan`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`namaPizza`);

--
-- Indexes for table `starters`
--
ALTER TABLE `starters`
  ADD PRIMARY KEY (`namaStarters`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `kode_pesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
