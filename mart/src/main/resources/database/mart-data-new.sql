-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.36 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table mart.attendance: ~0 rows (approximately)

-- Dumping data for table mart.debts: ~0 rows (approximately)

-- Dumping data for table mart.employees: ~4 rows (approximately)
INSERT INTO `employees` (`EmployeeID`, `FirstName`, `LastName`, `Position`, `DateOfBirth`, `ContactInfo`, `HireDate`, `Salary`, `CreatedBy`, `UpdatedBy`, `UpdatedAt`) VALUES
	(28, 'Sự', 'Nguyễn Thị Nhân', 'Quản Lý Tài Khoản', '1990-05-15', '1234567890', '2022-01-01', 5000.00, NULL, NULL, '2024-05-26 05:20:19'),
	(30, 'Kho', 'Trần Quốc', 'Quản Lý Kho Hàng', '1990-05-15', '1234567890', '2022-01-01', 5000.00, NULL, NULL, '2024-05-26 05:19:23'),
	(37, 'Toán', 'Nguyễn Kế', 'Thống Kê Doanh Thu Siêu Thị', '1999-05-14', '099999999', '2023-02-12', 2500.00, NULL, NULL, '2024-05-26 05:22:24'),
	(38, 'Dịch', 'Phùng Giao', 'Giao Dịch Với Khách Hàng', '1980-05-08', '088888888', '2023-02-11', 3500.00, NULL, NULL, '2024-05-26 05:24:13');

-- Dumping data for table mart.inventoryreceipts: ~0 rows (approximately)

-- Dumping data for table mart.orders: ~0 rows (approximately)

-- Dumping data for table mart.pricing: ~0 rows (approximately)

-- Dumping data for table mart.productimages: ~10 rows (approximately)
INSERT INTO `productimages` (`ImageID`, `ProductID`, `ImageUrl`, `CreatedAt`) VALUES
	(61, 89, 'kem que 1.jpg', '2024-05-26 05:50:33'),
	(62, 90, 'kem que 2.jpg', '2024-05-26 05:53:12'),
	(63, 91, 'quả táo.jpeg', '2024-05-26 05:54:53'),
	(64, 92, 'qua khế.jpg', '2024-05-26 05:55:10'),
	(65, 93, 'quả chuối.jpg', '2024-05-26 05:55:27'),
	(67, 95, 'bánh cosi.png', '2024-05-26 05:58:14'),
	(68, 96, 'bánh caro.png', '2024-05-26 05:58:28'),
	(69, 97, 'bánh nabaati.jpg', '2024-05-26 05:58:41'),
	(70, 98, 'quả chanh.png', '2024-05-26 05:59:29'),
	(71, 99, 'kem ốc quế dâu.png', '2024-05-26 06:00:46');

-- Dumping data for table mart.products: ~10 rows (approximately)
INSERT INTO `products` (`ProductID`, `ProductName`, `Category`, `StockQuantity`, `UnitPrice`, `SupplierID`, `CreatedBy`, `UpdatedBy`, `UpdatedAt`) VALUES
	(89, 'Kem chanh', 'Kem que', 48, 10.00, 21, 31, 31, '2024-05-26 06:03:40'),
	(90, 'Kem merio', 'Kem que', 50, 10.00, 21, 31, 31, '2024-05-26 05:53:12'),
	(91, 'Táo', 'Trái cây', 50, 20.00, 24, 31, 31, '2024-05-26 05:54:53'),
	(92, 'Khế', 'Trái cây', 48, 20.00, 24, 31, 31, '2024-05-26 06:03:32'),
	(93, 'Chuối', 'Trái cây', 50, 20.00, 24, 31, 31, '2024-05-26 05:55:27'),
	(95, 'Bánh cosi', 'Bánh', 50, 20.00, 22, 31, 31, '2024-05-26 05:58:14'),
	(96, 'Bánh caro', 'Bánh', 48, 20.00, 22, 31, 31, '2024-05-26 06:03:23'),
	(97, 'Bánh nabati', 'Bánh', 50, 20.00, 22, 31, 31, '2024-05-26 05:58:41'),
	(98, 'Chanh', 'Trái cây', 50, 10.00, 24, 31, 31, '2024-05-26 06:00:15'),
	(99, 'Kem ốc quế dâu', 'Kem ốc quế', 48, 10.00, 21, 31, 31, '2024-05-26 06:03:04');

-- Dumping data for table mart.roles: ~4 rows (approximately)
INSERT INTO `roles` (`RoleID`, `RoleName`, `Description`) VALUES
	(1, 'bộ phận nhân sự', 'bộ phận nhân sự'),
	(2, 'bộ phận giao dịch', 'bộ phận giao dịch'),
	(3, 'quản lý hàng', 'quản lý hàng'),
	(4, 'kế toán', 'kế toán');

-- Dumping data for table mart.salaryreports: ~0 rows (approximately)

-- Dumping data for table mart.salesreceiptdetails: ~4 rows (approximately)
INSERT INTO `salesreceiptdetails` (`DetailID`, `SalesReceiptID`, `ProductID`, `Quantity`, `UnitPrice`, `TotalPrice`, `CreatedBy`, `UpdatedBy`, `UpdatedAt`) VALUES
	(20, 16, 99, 2, 10.00, 20.00, 33, 33, '2024-05-26 06:03:04'),
	(21, 17, 96, 2, 20.00, 40.00, 33, 33, '2024-05-26 06:03:23'),
	(22, 18, 92, 2, 20.00, 40.00, 33, 33, '2024-05-26 06:03:32'),
	(23, 19, 89, 2, 10.00, 20.00, 33, 33, '2024-05-26 06:03:40');

-- Dumping data for table mart.salesreceipts: ~4 rows (approximately)
INSERT INTO `salesreceipts` (`SalesReceiptID`, `EmployeeID`, `SaleDate`, `TotalAmount`, `CustomerName`, `PaymentMethod`, `CreatedBy`, `UpdatedBy`, `UpdatedAt`) VALUES
	(16, 38, '2024-05-26', 20.00, 'Long', 'Tiền mặt', 33, 33, '2024-05-26 06:03:04'),
	(17, 38, '2024-05-26', 40.00, 'Tuấn', 'Tiền mặt', 33, 33, '2024-05-26 06:03:23'),
	(18, 38, '2024-05-26', 40.00, 'Tú', 'Tiền mặt', 33, 33, '2024-05-26 06:03:32'),
	(19, 38, '2024-05-26', 20.00, 'Long', 'Tiền mặt', 33, 33, '2024-05-26 06:03:40');

-- Dumping data for table mart.suppliers: ~4 rows (approximately)
INSERT INTO `suppliers` (`SupplierID`, `SupplierName`, `ContactInfo`, `Address`, `Phone`, `Email`, `CreatedBy`, `UpdatedBy`, `UpdatedAt`) VALUES
	(21, 'Công ty Cổ phần Kem Tràng Tiền', 'https://kemtrangtien.vn/', 'Số 35 Tràng Tiền, Hoàn Kiếm, TP. Hà Nội', '0986257979', 'kd@kemtrangtien.vn', 31, 31, '2024-05-26 05:41:24'),
	(22, 'Công Ty TNHH Cường Thịnh Fuhesa Food', 'https://cuongthinhfood.com/', '178 Trần Mai Ninh, P.12, Q.Tân Bình, HCM. Hồ Chí Minh Tân Bình 700000', ' 0782508889', 'nncuong88@gmail.com', 31, 31, '2024-05-26 05:42:36'),
	(23, 'Bánh Ống Đông Thành', 'https://trangvangvietnam.com/', 'Ấp Đông Thành (Thửa Đất Số 370, Tờ Bản Đồ Số 17), Xã Thành An, Huyện Mỏ Cày Bắc, Bến Tre, Việt Nam', ' 0849077879', 'nncuong88@gmail.com', 31, 31, '2024-05-26 05:44:20'),
	(24, 'Trái Cây Nhập Khẩu', 'https://www.eusfruit.vn/', ' Số 184 Việt Hưng, Phường Việt Hưng, Quận Long Biên, Thành phố Hà Nội, Việt Nam', ' 0339682182', 'contact@eustream.vn', 31, 31, '2024-05-26 05:46:00');

-- Dumping data for table mart.userroles: ~4 rows (approximately)
INSERT INTO `userroles` (`UserRoleID`, `UserID`, `RoleID`) VALUES
	(30, 30, 1),
	(31, 31, 3),
	(32, 32, 4),
	(33, 33, 2);

-- Dumping data for table mart.users: ~4 rows (approximately)
INSERT INTO `users` (`UserID`, `Username`, `PasswordHash`, `Email`, `EmployeeID`, `CreatedAt`) VALUES
	(30, 'nhansu', 'nhansu', 'nhansu@gmail.com', 28, '2024-05-26 04:15:00'),
	(31, 'kho', 'kho', 'kho@gmail.com', 30, '2024-05-26 05:21:04'),
	(32, 'ketoan', 'ketoan', 'ketoan@gmail.com', 37, '2024-05-26 05:22:52'),
	(33, 'giaodich', 'giaodich', 'giaodich@gmail.com', 38, '2024-05-26 05:24:32');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
