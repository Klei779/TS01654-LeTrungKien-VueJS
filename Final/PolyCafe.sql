CREATE DATABASE PolyCafe
GO
USE PolyCafe
GO

CREATE TABLE Categories(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Drinks(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Image NVARCHAR(50) NOT NULL,
    Available BIT NOT NULL,
    CategoryId NVARCHAR(20) NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(CategoryId) REFERENCES Categories(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

CREATE TABLE Cards(
    Id INT NOT NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Users(
    Username NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Photo NVARCHAR(50) NOT NULL,
    Manager BIT NOT NULL,
    PRIMARY KEY(Username)
)

CREATE TABLE Bills(
    Id BIGINT NOT NULL IDENTITY(10000, 1),
    Username NVARCHAR(20) NOT NULL,
    CardId INT NOT NULL,
    Checkin DATETIME NOT NULL,
    Checkout DATETIME NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Username) REFERENCES Users(Username) ON UPDATE CASCADE,
    FOREIGN KEY(CardId) REFERENCES Cards(Id) ON UPDATE CASCADE
)

CREATE TABLE BillDetails (
    Id BIGINT NOT NULL IDENTITY(100000, 1),
    BillId BIGINT NOT NULL,
    DrinkId NVARCHAR(20) NOT NULL,
    DrinkName NVARCHAR(50) NOT NULL, -- Thêm cột này để lưu tên tại thời điểm bán
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(BillId) REFERENCES Bills(Id)
        ON DELETE CASCADE,
    FOREIGN KEY(DrinkId) REFERENCES Drinks(Id)
        ON UPDATE CASCADE
);
GO

INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager)
VALUES 
    ('letrungkien', '123', 1, N'Lê Trung Kiên', 'avarta_Manager.png', 1),
    ('kientrungle', '456', 1, N'Trung Lê Kiên', 'avarta.png', 0),
    ('nv001', '123', 1, N'Nguyễn Văn A', 'user1.png', 0),
    ('nv002', '123', 1, N'Lê Thị B', 'user2.png', 0),
    ('nv003', '123', 1, N'Trần Văn C', 'user3.png', 0),
    ('nv004', '123', 1, N'Phạm Thị D', 'user4.png', 0);
GO

INSERT INTO Cards (Id, Status)
VALUES 
    (1, 1), -- Operating
    (2, 2), -- Lose
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 3), -- Error
	(7, 1),
    (8, 1),
    (9, 1),
	(10, 1),
    (11, 1),
    (12, 1),
	(13, 1),
    (14, 1),
    (15, 1),
	(16, 1),
    (17, 1),
	(18, 1),
    (19, 1),
    (20, 1);
GO

-- CHÈN CATEGORIES
INSERT INTO Categories (Id, Name) 
VALUES 
    ('C01', 'Cafe'),
    ('C02', 'Coke'),
    ('C03', 'Fruit Juice'),
    ('C04', 'Smoothie'),
    ('C05', 'TeaMilk');

-- CHÈN DRINKS (cũ và mới, mã chuẩn hóa)
INSERT INTO Drinks (Id, Name, UnitPrice, Discount, Image, Available, CategoryId)
VALUES
    ('FJ01', N'Nước trái cây #2x15', 27.0, 0.25, '', 0, 'C03'), -- Hết hàng
    ('FJ02', N'Nước trái cây #2x20', 225.2, 0.06, '', 1, 'C03'),
    ('FJ03', N'Nước trái cây #2x21', 408.5, 0.17, '', 1, 'C03'),
    ('FJ04', N'Nước trái cây #2x11', 99.9, 0.09, '', 1, 'C03'),
    ('FJ05', N'Nước trái cây #2x13', 910.7, 0.19, '', 1, 'C03'),
    ('C101', N'Cà phê đen đá', 25.0, 0.1, '', 1, 'C01'),
    ('C102', N'Cà phê sữa', 30.0, 0.05, '', 1, 'C01'),
    ('CK01', N'Coca Cola lon', 15.0, 0.0, '', 1, 'C02'),
    ('SM01', N'Sinh tố bơ', 40.0, 0.1, '', 1, 'C04'),
    ('SM02', N'Sinh tố xoài', 38.0, 0.08, '', 1, 'C04'),
    ('MT01', N'Trà sữa trân châu', 35.0, 0.05, '', 1, 'C05'),
	('C103', N'Cà phê sữa đá', 28.0, 0.04, '', 1, 'C01');

-- Chèn vào Bills (KHÔNG ghi rõ BillId)
INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status)
VALUES 
    ('nv001', 1, GETDATE(), NULL, 0),
    ('nv002', 2, GETDATE(), GETDATE(), 1),
    ('nv003', 3, GETDATE(), GETDATE(), 1),
    ('nv004', 4, GETDATE(), NULL, 0),
    ('letrungkien', 5, GETDATE(), GETDATE(), 1),
    ('nv001', 6, GETDATE(), GETDATE(), 1),
    ('nv002', 7, GETDATE(), GETDATE(), 1),
    ('nv003', 8, GETDATE(), GETDATE(), 1),
    ('nv001', 9, GETDATE(), GETDATE(), 1),
    ('nv004', 10, GETDATE(), GETDATE(), 1),
    ('nv003', 11, GETDATE(), GETDATE(), 1),
    ('letrungkien', 12, GETDATE(), GETDATE(), 1);

-- Chèn tạm vào BillDetails dùng các BillId gần đây nhất
-- KHÔNG an toàn nếu dữ liệu cũ đã có rồi
INSERT INTO BillDetails (BillId, DrinkId, DrinkName, UnitPrice, Discount, Quantity)
VALUES
    (IDENT_CURRENT('Bills') - 11, 'FJ01', N'Nước trái cây #2x15', 27.0, 0.25, 3),
    (IDENT_CURRENT('Bills') - 10, 'FJ02', N'Nước trái cây #2x20', 225.2, 0.06, 1),
    (IDENT_CURRENT('Bills') - 9,  'FJ03', N'Nước trái cây #2x21', 408.5, 0.17, 2),
    (IDENT_CURRENT('Bills') - 8,  'FJ04', N'Nước trái cây #2x11', 99.9, 0.09, 2),
    (IDENT_CURRENT('Bills') - 7,  'FJ05', N'Nước trái cây #2x13', 910.7, 0.19, 4),
    (IDENT_CURRENT('Bills') - 6,  'C101', N'Cà phê đen đá', 25.0, 0.1, 2),
    (IDENT_CURRENT('Bills') - 5,  'CK01', N'Coca Cola lon', 15.0, 0.0, 1),
    (IDENT_CURRENT('Bills') - 4,  'SM01', N'Sinh tố bơ', 40.0, 0.1, 1),
    (IDENT_CURRENT('Bills') - 3,  'SM02', N'Sinh tố xoài', 38.0, 0.08, 1),
    (IDENT_CURRENT('Bills') - 2,  'MT01', N'Trà sữa trân châu', 35.0, 0.05, 3),
    (IDENT_CURRENT('Bills') - 1,  'C102', N'Cà phê sữa', 30.0, 0.05, 1),
    (IDENT_CURRENT('Bills'),      'C103', N'Cà phê sữa đá', 28.0, 0.04, 2);



	DELETE FROM Users
	DELETE FROM Cards
	DELETE FROM Categories
	DELETE FROM Drinks
	DELETE FROM Bills
	DELETE FROM BillDetails

	SELECT * FROM Users
	SELECT * FROM Cards
	SELECT * FROM Categories
	SELECT * FROM Drinks
	SELECT * FROM Bills
	SELECT * FROM BillDetails