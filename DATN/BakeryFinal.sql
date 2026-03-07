CREATE DATABASE BakeryFinal
USE BakeryFinal

-- 1. GroundWork (gi? nguyên)
CREATE TABLE GroundWork (
  GroundWorkID INT PRIMARY KEY,
  Name NVARCHAR(100) NOT NULL,
  Address NVARCHAR(100),
  Phone NVARCHAR(15),
  OpenDate DATE,
  Status NVARCHAR(50)
);

-- 2. Users
CREATE TABLE Users (
  ID NVARCHAR(50) PRIMARY KEY,
  Password NVARCHAR(50) NOT NULL,
  Fullname NVARCHAR(100),
  Photo NVARCHAR(100),
  Role INT, -- 0: user, 1: manager
  Enabled BIT,
  GroundWorkID INT
);

-- 3. Category (gi? nguyên)
CREATE TABLE Category (
  ID INT PRIMARY KEY,
  Name NVARCHAR(50) NOT NULL
);

-- 4. Card (gi? nguyên)
CREATE TABLE Card (
  ID INT PRIMARY KEY,
  Status INT
);

-- 5. MaterialType (gi? nguyên)
CREATE TABLE MaterialType (
  TypeID INT PRIMARY KEY,
  TypeName NVARCHAR(50) NOT NULL
);

-- 6. Material
CREATE TABLE Material (
  MaterialID INT PRIMARY KEY,
  Name NVARCHAR(100),
  Unit NVARCHAR(20),
  Quantity FLOAT,
  Supplier NVARCHAR(100),
  ImportDate DATE,
  Photo NVARCHAR(100),
  TypeID INT,
  Note NVARCHAR(200)
);

-- 7. Bakery
CREATE TABLE Bakery (
  ID INT PRIMARY KEY,
  Name NVARCHAR(100),
  UnitPrice FLOAT,
  Discount FLOAT,
  Photo NVARCHAR(100),
  Available BIT,
  CategoryID INT,
  Note NVARCHAR(200)
);

-- 8. BakeryMaterial (gi? nguyên)
CREATE TABLE BakeryMaterial (
  ID INT PRIMARY KEY,
  BakeryID INT NOT NULL,
  MaterialID INT NOT NULL,
  Quantity FLOAT,
  Unit NVARCHAR(20)
);

-- 9. Bill
CREATE TABLE Bill (
  ID INT PRIMARY KEY,
  CheckIn DATE NOT NULL,
  CheckOut DATE NULL,
  Status BIT NOT NULL DEFAULT 0,
  Username NVARCHAR(50) NOT NULL,
  CardID INT NULL,
  PaymentMethod INT NULL,
  GroundWorkID INT NOT NULL
);


CREATE TABLE BillDetails (
  ID INT IDENTITY(1,1) PRIMARY KEY,
  BillID INT NOT NULL,
  BakeryID INT NOT NULL,
  ProductName NVARCHAR(100),
  Unit NVARCHAR(20),
  Quantity FLOAT,
  UnitPrice FLOAT,
  Discount FLOAT,
  FinalPrice FLOAT
);

-- USERS
ALTER TABLE Users
ADD CONSTRAINT FK_Users_GroundWork FOREIGN KEY (GroundWorkID) REFERENCES GroundWork(GroundWorkID);

-- MATERIAL
ALTER TABLE Material
ADD CONSTRAINT FK_Material_Type FOREIGN KEY (TypeID) REFERENCES MaterialType(TypeID);

-- BAKERY
ALTER TABLE Bakery
ADD CONSTRAINT FK_Bakery_Category FOREIGN KEY (CategoryID) REFERENCES Category(ID);

-- BAKERYMATERIAL
ALTER TABLE BakeryMaterial
ADD CONSTRAINT FK_BakeryMaterial_Bakery FOREIGN KEY (BakeryID) REFERENCES Bakery(ID),
    CONSTRAINT FK_BakeryMaterial_Material FOREIGN KEY (MaterialID) REFERENCES Material(MaterialID);

-- BILL
ALTER TABLE Bill
ADD CONSTRAINT FK_Bill_Users FOREIGN KEY (Username) REFERENCES Users(ID),
    CONSTRAINT FK_Bill_Card FOREIGN KEY (CardID) REFERENCES Card(ID),
    CONSTRAINT FK_Bill_GroundWork FOREIGN KEY (GroundWorkID) REFERENCES GroundWork(GroundWorkID);

-- BILLDETAILS
ALTER TABLE BillDetails
ADD CONSTRAINT FK_BillDetails_Bill FOREIGN KEY (BillID) REFERENCES Bill(ID),
    CONSTRAINT FK_BillDetails_Bakery FOREIGN KEY (BakeryID) REFERENCES Bakery(ID);

	INSERT INTO GroundWork VALUES
(1, N'C? s? Hà N?i', N'123 Tr?n Duy H?ng', '0911002200', '2022-01-01', N'?ang ho?t ??ng'),
(2, N'C? s? HCM', N'456 Lê L?i', '0902333444', '2023-03-01', N'?ang ho?t ??ng'),
(3, N'C? s? ?à N?ng', N'789 Nguy?n V?n Linh', '0938555999', '2022-05-15', N'?ang ho?t ??ng'),
(4, N'C? s? C?n Th?', N'23 Hùng V??ng', '0988777666', '2021-08-10', N'Ng?ng ho?t ??ng'),
(5, N'C? s? H?i Phòng', N'77 L?ch Tray', '0969123456', '2024-01-20', N'?ang ho?t ??ng');
GO

INSERT INTO Users VALUES
('admin', 'admin123', N'Qu?n tr? viên', 'admin.jpg', 1, 1, 1),
('user01', '123456', N'Nguy?n V?n A', 'user1.jpg', 0, 1, 1),
('user02', '123456', N'Tr?n Th? B', 'user2.jpg', 0, 1, 2),
('manager01', 'admin456', N'Lê Quang C', 'manager1.jpg', 1, 1, 2),
('user03', '123456', N'Ph?m V?n D', 'user3.jpg', 0, 0, 3);
GO

INSERT INTO Category VALUES
(1, N'Bánh kem'),
(2, N'Bánh mì'),
(3, N'Bánh ng?t'),
(4, N'Bánh quy'),
(5, N'Bánh trung thu');
GO

INSERT INTO Card VALUES
(1, 0),
(2, 1),
(3, 0),
(4, 1),
(5, 0);
GO

INSERT INTO MaterialType VALUES
(1, N'T??i'),
(2, N'Khô'),
(3, N'Gia v?'),
(4, N'Ch?t b?o qu?n'),
(5, N'?óng gói');
GO

INSERT INTO Material VALUES
(1, N'B?t mì', N'kg', 100, N'ABC Corp', '2025-07-01', 'botmi.jpg', 1, N'Lo?i t?t'),
(2, N'Tr?ng gà', N'qu?', 200, N'XYZ Farm', '2025-07-02', 'trung.jpg', 1, N''),
(3, N'???ng', N'kg', 50, N'Ng?t Ngào', '2025-07-03', 'duong.jpg', 2, N''),
(4, N'B?', N'g', 1000, N'Pháp Corp', '2025-07-04', 'bo.jpg', 1, N''),
(5, N'Màu th?c ph?m', N'ml', 500, N'MixColor', '2025-07-05', 'mau.jpg', 4, N'?? màu');
GO

INSERT INTO Bakery VALUES
(1, N'Bánh gato', 50000, 0.1, 'gato.jpg', 1, 1, N'Bán ch?y nh?t'),
(2, N'Bánh mì Pháp', 15000, 0.05, 'banhmip.jpg', 1, 2, N''),
(3, N'Bánh cupcake', 25000, 0.15, 'cupcake.jpg', 1, 3, N'M?u m?i'),
(4, N'Bánh quy b?', 30000, 0.2, 'quy.jpg', 1, 4, N''),
(5, N'Bánh trung thu th?p c?m', 80000, 0.25, 'trungthu.jpg', 0, 5, N'Có tr?ng mu?i');
GO

INSERT INTO BakeryMaterial VALUES
(1, 1, 1, 0.2, N'kg'),
(2, 1, 2, 2, N'qu?'),
(3, 2, 1, 0.1, N'kg'),
(4, 3, 1, 0.15, N'kg'),
(5, 4, 3, 0.05, N'kg');
GO

INSERT INTO Bill VALUES
(1, '2025-08-01', '2025-08-01', 1, 'user01', 1, 1, 1),
(2, '2025-08-02', NULL, 0, 'user02', 2, 0, 2),
(3, '2025-08-03', '2025-08-03', 1, 'user01', 3, 1, 1),
(4, '2025-08-04', NULL, 0, 'user03', NULL, 2, 3),
(5, '2025-08-05', NULL, 0, 'admin', 4, 1, 1);
GO

INSERT INTO BillDetails VALUES
(1, 1, N'Bánh gato', N'Cái', 1, 50000, 0.1, 45000),
(2, 3, N'Bánh cupcake', N'Cái', 2, 25000, 0.15, 42500),
(3, 2, N'Bánh mì Pháp', N'Cái', 3, 15000, 0.05, 42750),
(4, 4, N'Bánh quy b?', N'H?p', 1, 30000, 0.2, 24000),
(5, 1, N'Bánh gato', N'Cái', 2, 50000, 0.1, 90000);
GO

