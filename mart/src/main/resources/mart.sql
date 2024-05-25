-- Bảng: Employees (Nhân viên)
CREATE TABLE Employees
(
    EmployeeID  INT PRIMARY KEY AUTO_INCREMENT,
    FirstName   VARCHAR(50)    NOT NULL,
    LastName    VARCHAR(50)    NOT NULL,
    Position    VARCHAR(50)    NOT NULL,
    DateOfBirth DATE,
    ContactInfo VARCHAR(100),
    HireDate    DATE           NOT NULL,
    Salary      DECIMAL(10, 2) NOT NULL,
    CreatedBy   INT,
    UpdatedBy   INT,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng: Users (Người dùng)
CREATE TABLE Users
(
    UserID       INT PRIMARY KEY AUTO_INCREMENT,
    Username     VARCHAR(50) UNIQUE  NOT NULL,
    PasswordHash VARCHAR(255)        NOT NULL,
    Email        VARCHAR(100) UNIQUE NOT NULL,
    EmployeeID   INT                 NOT NULL,
    CreatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID)
);

-- Bảng: Roles (Quyền)
CREATE TABLE Roles
(
    RoleID      INT PRIMARY KEY AUTO_INCREMENT,
    RoleName    VARCHAR(50) UNIQUE NOT NULL,
    Description VARCHAR(255)
);

-- Bảng: UserRoles (Quyền của người dùng)
CREATE TABLE UserRoles
(
    UserRoleID INT PRIMARY KEY AUTO_INCREMENT,
    UserID     INT NOT NULL,
    RoleID     INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users (UserID),
    FOREIGN KEY (RoleID) REFERENCES Roles (RoleID)
);

-- Bảng: Attendance (Chấm công)
CREATE TABLE Attendance
(
    AttendanceID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID   INT           NOT NULL,
    Date         DATE          NOT NULL,
    HoursWorked  DECIMAL(4, 2) NOT NULL,
    Shift        VARCHAR(50),
    CreatedBy    INT,
    UpdatedBy    INT,
    UpdatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);


-- Bảng: Suppliers (Nhà cung cấp)
CREATE TABLE Suppliers
(
    SupplierID   INT PRIMARY KEY AUTO_INCREMENT,
    SupplierName VARCHAR(100) NOT NULL,
    ContactInfo  VARCHAR(100),
    Address      VARCHAR(255),
    Phone        VARCHAR(20),
    Email        VARCHAR(100),
    CreatedBy    INT,
    UpdatedBy    INT,
    UpdatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: Products (Sản phẩm)
CREATE TABLE Products
(
    ProductID     INT PRIMARY KEY AUTO_INCREMENT,
    ProductName   VARCHAR(100)   NOT NULL,
    Category      VARCHAR(50),
    StockQuantity INT            NOT NULL,
    UnitPrice     DECIMAL(10, 2) NOT NULL,
    SupplierID    INT,
    CreatedBy     INT,
    UpdatedBy     INT,
    UpdatedAt     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers (SupplierID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: Pricing (Giá bán)
CREATE TABLE Pricing
(
    PriceID   INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT            NOT NULL,
    Price     DECIMAL(10, 2) NOT NULL,
    StartDate DATE           NOT NULL,
    EndDate   DATE,
    CreatedBy INT,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: Orders (Đơn hàng)
CREATE TABLE Orders
(
    OrderID     INT PRIMARY KEY AUTO_INCREMENT,
    SupplierID  INT            NOT NULL,
    OrderDate   DATE           NOT NULL,
    Status      VARCHAR(50)    NOT NULL,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    CreatedBy   INT,
    UpdatedBy   INT,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers (SupplierID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: ProductImages (Hình ảnh sản phẩm)
CREATE TABLE ProductImages
(
    ImageID   INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT          NOT NULL,
    ImageUrl  VARCHAR(255) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID)
);

-- Bảng: InventoryReceipts (Phiếu nhập hàng)
CREATE TABLE InventoryReceipts
(
    ReceiptID   INT PRIMARY KEY AUTO_INCREMENT,
    OrderID     INT            NOT NULL,
    ReceiptDate DATE           NOT NULL,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    CreatedBy   INT,
    UpdatedBy   INT,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: SalesReceipts (Phiếu bán hàng)
CREATE TABLE SalesReceipts
(
    SalesReceiptID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID     INT            NOT NULL,
    SaleDate       DATE           NOT NULL,
    TotalAmount    DECIMAL(10, 2) NOT NULL,
    CustomerName   VARCHAR(100),
    PaymentMethod  VARCHAR(50),
    CreatedBy      INT,
    UpdatedBy      INT,
    UpdatedAt      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: SalesReceiptDetails (Chi tiết phiếu bán hàng)
CREATE TABLE SalesReceiptDetails
(
    DetailID       INT PRIMARY KEY AUTO_INCREMENT,
    SalesReceiptID INT            NOT NULL,
    ProductID      INT            NOT NULL,
    Quantity       INT            NOT NULL,
    UnitPrice      DECIMAL(10, 2) NOT NULL,
    TotalPrice     DECIMAL(10, 2) NOT NULL,
    CreatedBy      INT,
    UpdatedBy      INT,
    UpdatedAt      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (SalesReceiptID) REFERENCES SalesReceipts (SalesReceiptID),
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: Debts (Công nợ)
CREATE TABLE Debts
(
    DebtID     INT PRIMARY KEY AUTO_INCREMENT,
    SupplierID INT            NOT NULL,
    Amount     DECIMAL(10, 2) NOT NULL,
    DueDate    DATE           NOT NULL,
    Status     VARCHAR(50),
    CreatedBy  INT,
    UpdatedBy  INT,
    UpdatedAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers (SupplierID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);

-- Bảng: SalaryReports (Báo cáo lương)
CREATE TABLE SalaryReports
(
    ReportID    INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID  INT            NOT NULL,
    ReportDate  DATE           NOT NULL,
    Period VARCHAR (50) NOT NULL,
    TotalSalary DECIMAL(10, 2) NOT NULL,
    CreatedBy   INT,
    UpdatedBy   INT,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID),
    FOREIGN KEY (CreatedBy) REFERENCES Users (UserID),
    FOREIGN KEY (UpdatedBy) REFERENCES Users (UserID)
);
