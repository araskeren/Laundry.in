-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 04 Jul 2017 pada 06.50
-- Versi Server: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jenislaundry`
--

CREATE TABLE `tb_jenislaundry` (
  `id` int(5) NOT NULL,
  `jenis_laundry` varchar(12) NOT NULL,
  `tipe_laundry` varchar(12) NOT NULL,
  `item` varchar(12) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_jenislaundry`
--

INSERT INTO `tb_jenislaundry` (`id`, `jenis_laundry`, `tipe_laundry`, `item`, `harga`) VALUES
(1, 'Biasa/Umum', 'Cuci Basah', 'Pakaian', 3000),
(3, 'Biasa/Umum', 'Cuci Kering', 'Pakaian', 2500),
(4, 'Khusus', 'Komplit', 'Jas', 5000),
(5, 'Khusus', 'Cuci+Strika', 'Jas', 3500),
(6, 'Khusus', 'Setrika', 'Jas', 3000),
(7, 'Biasa/Umum', 'Cuci Kering', 'Jas', 2500),
(8, 'Biasa/Umum', 'Cuci Kering', 'Sprei', 3500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pelanggan`
--

CREATE TABLE `tb_pelanggan` (
  `id` int(5) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `kontak` varchar(12) NOT NULL,
  `tgl_daftar` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pelanggan`
--

INSERT INTO `tb_pelanggan` (`id`, `nama`, `alamat`, `kontak`, `tgl_daftar`) VALUES
(101, 'Damis', 'tumpang 1', '087834927351', '2017-06-14 00:00:00'),
(103, 'Arum Maulidiniah', 'bla bla', '083842350963', '2017-05-09 00:00:00'),
(105, 'Wafa Arsy', 'Jln Imam Banjol No 12', '08783412354', '2017-06-29 02:01:58'),
(106, 'Damis', 'Jl.Bla bla bla', '087834927351', '2017-07-04 00:52:49');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(3) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `level` varchar(10) NOT NULL,
  `shift` varchar(5) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `nama`, `password`, `level`, `shift`, `create_date`) VALUES
(101, 'Damis', '', 'Admin', 'Pagi', '2017-06-24 16:40:16'),
(102, 'Arum', 'arum', 'Pegawai', 'All', '2017-06-24 16:42:09'),
(103, 'Axel', 'axel', 'Trial', 'All', '2017-06-24 16:48:37'),
(105, 'Bagas', 'bagas', 'Trial', 'Pagi', '2017-06-24 17:20:20');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tr_cuci`
--

CREATE TABLE `tr_cuci` (
  `No_Faktur` int(12) NOT NULL,
  `id_JenisLaundry` int(3) NOT NULL,
  `jenis_parfum` varchar(15) NOT NULL,
  `satuan_ukur` varchar(6) NOT NULL,
  `jml` int(2) NOT NULL,
  `harga` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tr_cuci`
--

INSERT INTO `tr_cuci` (`No_Faktur`, `id_JenisLaundry`, `jenis_parfum`, `satuan_ukur`, `jml`, `harga`) VALUES
(1120, 1, 'Molto', 'KG', 2, 6000),
(1120, 1, 'Lavender', 'Satuan', 3, 9000),
(1120, 1, 'Molto', 'KG', 5, 15000),
(1120, 1, 'Lavender', 'KG', 2, 6000),
(1122, 1, 'Lavender', 'KG', 5, 15000),
(1123, 1, 'Lavender', 'KG', 5, 15000),
(1124, 1, 'Lavender', 'KG', 5, 15000),
(111101, 1, 'Molto', 'Kg', 5, 20000),
(111101, 1, 'Lavender', 'Satuan', 5, 25000),
(111113, 1, 'Lavender', 'KG', 1, 3000),
(111113, 6, 'Lavender', 'KG', 1, 5000),
(111114, 1, 'Lavender', 'KG', 1, 3000),
(111114, 4, 'Molto', 'KG', 1, 5000),
(111115, 1, 'Lavender', 'KG', 1, 3000),
(111115, 6, 'Lavender', 'KG', 1, 5000),
(111116, 1, 'Lavender', 'KG', 1, 3000),
(111116, 4, 'Lavender', 'Satuan', 1, 5000),
(111117, 1, 'Lavender', 'KG', 1, 3000),
(111117, 1, 'Molto', 'KG', 1, 3000),
(111118, 1, 'Lavender', 'KG', 1, 3000),
(111119, 1, 'Lavender', 'KG', 1, 3000),
(111120, 1, 'Lavender', 'KG', 1, 3000),
(111120, 6, 'Lavender', 'KG', 1, 5000),
(111120, 6, 'Lavender', 'KG', 1, 5000),
(111120, 1, 'Lavender', 'KG', 1, 3000),
(111120, 4, 'Lavender', 'KG', 1, 5000),
(111121, 1, 'Lavender', 'KG', 1, 3000),
(111122, 0, 'Lavender', 'KG', 1, 2500),
(111122, 4, 'Lavender', 'KG', 4, 20000),
(111123, 0, 'Lavender', 'KG', 1, 2500),
(111123, 4, 'Lavender', 'KG', 4, 20000),
(111124, 0, 'Lavender', 'KG', 1, 2500),
(111124, 4, 'Lavender', 'KG', 4, 20000),
(111125, 1, 'Lavender', 'KG', 1, 3000),
(111125, 4, 'Lavender', 'KG', 1, 5000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tr_monitoring`
--

CREATE TABLE `tr_monitoring` (
  `ID_Log` int(7) NOT NULL,
  `ID_User` int(3) NOT NULL,
  `tgl_login` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Tgl_Logout` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tr_monitoring`
--

INSERT INTO `tr_monitoring` (`ID_Log`, `ID_User`, `tgl_login`, `Tgl_Logout`) VALUES
(1000018, 101, '2017-07-03 10:46:42', '2017-07-03 17:24:00'),
(1000019, 101, '2017-07-03 15:37:38', '2017-07-03 15:40:16'),
(1000020, 101, '2017-07-03 15:39:46', '2017-07-03 22:32:12'),
(1000021, 101, '2017-07-03 19:22:19', '2017-07-04 00:49:36'),
(1000022, 101, '2017-07-04 00:47:04', '2017-07-04 00:49:33'),
(1000023, 102, '2017-07-04 00:48:14', '2017-07-04 00:55:08'),
(1000024, 101, '2017-07-04 00:52:26', '2017-07-04 00:57:14'),
(1000025, 102, '2017-07-04 00:55:17', '2017-07-04 00:57:55'),
(1000026, 102, '2017-07-04 00:57:24', '2017-07-04 01:06:32'),
(1000027, 102, '2017-07-04 01:03:42', '2017-07-04 01:08:06'),
(1000028, 101, '2017-07-04 01:06:45', '2017-07-04 01:12:21'),
(1000029, 101, '2017-07-04 01:11:08', '2017-07-04 01:21:02'),
(1000030, 101, '2017-07-04 01:14:33', '2017-07-04 01:29:20'),
(1000031, 101, '2017-07-04 01:24:20', '2017-07-04 01:38:37'),
(1000032, 101, '2017-07-04 01:29:27', '2017-07-04 01:40:16'),
(1000033, 101, '2017-07-04 01:38:50', '2017-07-04 01:53:13'),
(1000034, 101, '2017-07-04 01:40:20', '2017-07-04 01:57:45'),
(1000035, 101, '2017-07-04 01:56:49', '2017-07-04 02:01:42'),
(1000036, 101, '2017-07-04 02:00:19', '2017-07-04 02:38:31'),
(1000037, 101, '2017-07-04 02:37:08', '2017-07-04 02:39:55'),
(1000038, 101, '2017-07-04 02:39:14', '2017-07-04 02:42:35'),
(1000039, 101, '2017-07-04 02:42:13', '2017-07-04 02:45:18'),
(1000040, 101, '2017-07-04 02:44:05', '2017-07-04 02:46:38'),
(1000041, 101, '2017-07-04 02:46:25', '2017-07-04 02:47:23'),
(1000042, 101, '2017-07-04 02:47:07', '2017-07-04 02:50:00'),
(1000043, 101, '2017-07-04 02:49:12', '2017-07-04 02:51:54'),
(1000044, 101, '2017-07-04 02:51:13', '2017-07-04 02:54:53'),
(1000045, 101, '2017-07-04 02:54:11', '2017-07-04 02:57:54'),
(1000046, 101, '2017-07-04 02:57:14', '2017-07-04 03:03:27'),
(1000047, 101, '2017-07-04 02:59:48', '2017-07-04 10:27:39'),
(1000048, 101, '2017-07-04 10:27:15', '2017-07-04 10:28:26'),
(1000049, 101, '2017-07-04 10:28:05', '2017-07-04 10:33:22'),
(1000050, 101, '2017-07-04 10:32:56', '2017-07-04 10:40:46'),
(1000051, 101, '2017-07-04 10:39:57', '2017-07-04 10:46:50'),
(1000052, 101, '2017-07-04 10:42:57', '2017-07-04 10:49:48'),
(1000053, 101, '2017-07-04 10:47:35', '2017-07-04 10:51:05'),
(1000054, 101, '2017-07-04 10:50:55', '2017-07-04 10:55:51'),
(1000055, 101, '2017-07-04 10:51:22', '2017-07-04 11:17:36'),
(1000056, 101, '2017-07-04 10:56:10', '2017-07-04 11:18:17'),
(1000057, 101, '2017-07-04 11:17:45', '2017-07-04 11:21:38'),
(1000058, 101, '2017-07-04 11:18:25', '2017-07-04 11:33:36'),
(1000059, 101, '2017-07-04 11:31:18', '2017-07-04 11:45:04'),
(1000060, 101, '2017-07-04 11:33:43', '2017-07-04 11:48:07'),
(1000061, 101, '2017-07-04 11:45:09', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tr_pencucian`
--

CREATE TABLE `tr_pencucian` (
  `no_faktur` int(12) NOT NULL,
  `id_pelanggan` int(6) NOT NULL,
  `nama_pelanggan` varchar(25) NOT NULL,
  `tgl_order` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tgl_update` datetime DEFAULT NULL,
  `estimasi` int(2) NOT NULL,
  `pengambilan` varchar(15) NOT NULL,
  `diskon` int(12) NOT NULL,
  `keterangan` text NOT NULL,
  `status_pemesanan` varchar(18) NOT NULL,
  `total_bayar` int(15) NOT NULL,
  `jumlah_dibayar` int(15) NOT NULL,
  `kembalian` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tr_pencucian`
--

INSERT INTO `tr_pencucian` (`no_faktur`, `id_pelanggan`, `nama_pelanggan`, `tgl_order`, `tgl_update`, `estimasi`, `pengambilan`, `diskon`, `keterangan`, `status_pemesanan`, `total_bayar`, `jumlah_dibayar`, `kembalian`) VALUES
(111101, 101, 'Damis', '2017-06-09 00:00:00', '2017-07-01 04:46:11', 5, 'Exspres', 2000, 'Tidak ada', 'Sudah Di Ambil', 50000, 3000, 1500),
(111107, 101, 'Damis', '2017-06-09 00:00:00', '0000-00-00 00:00:00', 5, 'Diambil Sendiri', 5, 'Bla bla', 'Belum Di Cuci', 15000, 20000, 5000),
(111118, 103, 'Arum Maulidiniah', '2017-06-28 00:30:20', '2017-07-01 04:45:39', 1, 'Diambil Sendiri', 20, '', 'Belum Di Ambil', 2400, 4000, 1600),
(111120, 105, 'Wafa Arsy', '2017-07-02 11:26:07', '2017-07-02 11:27:11', 1, 'Diambil Sendiri', 4000, '', 'Di Proses', 4000, 8000, 4000),
(111121, 101, 'Damis', '2017-07-04 01:20:32', NULL, 1, 'Diambil Sendiri', 600, '', 'Belum Di Cuci', 2400, 5000, 2600),
(111123, 103, 'Arum Maulidiniah', '2017-07-04 01:25:31', NULL, 1, 'Diambil Sendiri', 2250, '', 'Belum Di Cuci', 20250, 23000, 2750),
(111124, 103, 'Arum Maulidiniah', '2017-07-04 01:26:19', '2017-07-04 10:45:21', 1, 'Diambil Sendiri', 2250, '', 'Sudah Di Ambil', 20250, 23000, 2750),
(111125, 101, 'Damis', '2017-07-04 02:01:05', '2017-07-04 10:40:29', 1, 'Diambil Sendiri', 400, 'Tidak ada', 'Sudah Di Ambil', 7600, 10000, 2400);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_jenislaundry`
--
ALTER TABLE `tb_jenislaundry`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indexes for table `tr_cuci`
--
ALTER TABLE `tr_cuci`
  ADD KEY `No_Faktur` (`No_Faktur`);

--
-- Indexes for table `tr_monitoring`
--
ALTER TABLE `tr_monitoring`
  ADD PRIMARY KEY (`ID_Log`);

--
-- Indexes for table `tr_pencucian`
--
ALTER TABLE `tr_pencucian`
  ADD PRIMARY KEY (`no_faktur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_jenislaundry`
--
ALTER TABLE `tb_jenislaundry`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;
--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;
--
-- AUTO_INCREMENT for table `tr_monitoring`
--
ALTER TABLE `tr_monitoring`
  MODIFY `ID_Log` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000062;
--
-- AUTO_INCREMENT for table `tr_pencucian`
--
ALTER TABLE `tr_pencucian`
  MODIFY `no_faktur` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111126;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
