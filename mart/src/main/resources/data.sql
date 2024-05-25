-- Thêm dữ liệu cho bảng Employees
INSERT INTO Employees (FirstName, LastName, Position, DateOfBirth, ContactInfo, HireDate, Salary, CreatedBy, UpdatedBy)
VALUES
    ('John', 'Doe', 'Cashier', '1990-05-15', '1234567890', '2022-01-01', 2500.00, 1, 1),
    ('Jane', 'Smith', 'Manager', '1985-10-20', '0987654321', '2021-12-01', 4000.00, 1, 1),
    ('Mike', 'Johnson', 'Sales Associate', '1995-03-12', '9876543210', '2022-03-15', 2000.00, 1, 1),
    ('Emily', 'Brown', 'Stock Clerk', '1993-07-22', '4567890123', '2023-02-10', 2200.00, 1, 1);

-- Thêm dữ liệu cho bảng Users
INSERT INTO Users (Username, PasswordHash, Email, EmployeeID)
VALUES
    ('john_doe', 'password_hash', 'john@example.com', 1),
    ('jane_smith', 'password_hash', 'jane@example.com', 2),
    ('mike_johnson', 'password_hash', 'mike@example.com', 3),
    ('emily_brown', 'password_hash', 'emily@example.com', 4);

-- Thêm dữ liệu cho bảng Roles
INSERT INTO Roles (RoleName, Description)
VALUES
    ('bộ phận nhân sự', 'bộ phận nhân sự'),
    ('bộ phận cung ứng', 'bộ phận cung ứng'),
    ('quản lý hàng', 'quản lý hàng'),
    ('kế toán', 'kế toán'),
    ('Admin', 'Admin');

-- Thêm dữ liệu cho bảng UserRoles
INSERT INTO UserRoles (UserID, RoleID)
VALUES
    (1, 1),
    (2, 1),
    (3, 3),
    (4, 3);

-- Thêm dữ liệu cho bảng Suppliers
INSERT INTO Suppliers (SupplierName, ContactInfo, Address, Phone, Email, CreatedBy, UpdatedBy)
VALUES
    ('Supplier A', 'Contact Info A', 'Address A', '123456789', 'supplier_a@example.com', 1, 1),
    ('Supplier B', 'Contact Info B', 'Address B', '987654321', 'supplier_b@example.com', 1, 1);

-- Thêm dữ liệu cho bảng Attendance
INSERT INTO Attendance (EmployeeID, Date, HoursWorked, Shift, CreatedBy, UpdatedBy)
VALUES
    (1, '2024-05-01', 8, 'Morning', 1, 1),
    (2, '2024-05-01', 8, 'Morning', 1, 1),
    (3, '2024-05-01', 8, 'Morning', 1, 1),
    (4, '2024-05-01', 8, 'Morning', 1, 1);

-- Thêm dữ liệu cho bảng Products
INSERT INTO Products (ProductName, Category, StockQuantity, UnitPrice, SupplierID, CreatedBy, UpdatedBy)
VALUES
    ('Product A', 'Category A', 100, 10.00, 1, 1, 1),
    ('Product B', 'Category B', 150, 15.00, 1, 1, 1),
    ('Product C', 'Category C', 200, 20.00, 2, 1, 1);

-- Thêm dữ liệu cho bảng Pricing
INSERT INTO Pricing (ProductID, Price, StartDate, EndDate, CreatedBy, UpdatedBy)
VALUES
    (1, 12.00, '2024-05-01', NULL, 1, 1),
    (2, 18.00, '2024-05-01', NULL, 1, 1),
    (3, 22.00, '2024-05-01', NULL, 1, 1);

-- Thêm dữ liệu cho bảng Orders
INSERT INTO Orders (SupplierID, OrderDate, Status, TotalAmount, CreatedBy, UpdatedBy)
VALUES
    (1, '2024-05-01', 'Pending', 1200.00, 1, 1),
    (2, '2024-05-01', 'Pending', 1800.00, 1, 1);

-- Thêm dữ liệu cho bảng ProductImages
INSERT INTO ProductImages (ProductID, ImageUrl)
VALUES
    (1, 'image_url_1'),
    (2, 'image_url_2'),
    (3, 'image_url_3');

-- Thêm dữ liệu cho bảng SalesReceipts
INSERT INTO SalesReceipts (EmployeeID, SaleDate, TotalAmount, CustomerName, PaymentMethod, CreatedBy, UpdatedBy)
VALUES
    (3, '2024-05-01', 120.00, 'Customer A', 'Cash', 1, 1),
    (3, '2024-05-02', 150.00, 'Customer B', 'Credit Card', 1, 1),
    (4, '2024-05-03', 200.00, 'Customer C', 'Cash', 1, 1);

-- Thêm dữ liệu cho bảng SalesReceiptDetails
INSERT INTO SalesReceiptDetails (SalesReceiptID, ProductID, Quantity, UnitPrice, TotalPrice, CreatedBy, UpdatedBy)
VALUES
    (1, 1, 5, 12.00, 60.00, 1, 1),
    (2, 2, 7, 18.00, 126.00, 1, 1),
    (3, 3, 4, 22.00, 88.00, 1, 1);

-- Thêm dữ liệu cho bảng Debts
INSERT INTO Debts (SupplierID, Amount, DueDate, Status, CreatedBy, UpdatedBy)
VALUES
    (1, 800.00, '2024-06-01', 'Pending', 1, 1),
    (2, 1200.00, '2024-06-15', 'Pending', 1, 1);

-- Thêm dữ liệu cho bảng SalaryReports
INSERT INTO SalaryReports (EmployeeID, ReportDate, Period, TotalSalary, CreatedBy, UpdatedBy)
VALUES
    (1, '2024-05-31', 'Monthly', 2500.00, 1, 1),
    (2, '2024-05-31', 'Monthly', 4000.00, 1, 1),
    (3, '2024-05-31', 'Monthly', 2000.00, 1, 1),
    (4, '2024-05-31', 'Monthly', 2200.00, 1, 1);

-- Thêm dữ liệu cho bảng InventoryReceipts
INSERT INTO InventoryReceipts (OrderID, ReceiptDate, TotalAmount, CreatedBy, UpdatedBy)
VALUES
    (1, '2024-05-02', 1200.00, 1, 1),
    (2, '2024-05-03', 1800.00, 1, 1);





