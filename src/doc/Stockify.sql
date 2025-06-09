CREATE SCHEMA IF NOT EXISTS stockify;

#DROP SCHEMA stockify;

use stockify;

-- Tabela: Products
CREATE TABLE  IF NOT EXISTS Products (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(150) NOT NULL,
    Category VARCHAR(100),
    Price DOUBLE,
    Quantity INT
);

-- Tabela: Inputs
CREATE TABLE  IF NOT EXISTS Inputs (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_Product INT,
    Quantity INT,
    Input_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Supplier VARCHAR(150),
    FOREIGN KEY (ID_Product) REFERENCES Products(ID) ON DELETE CASCADE
);

-- Tabela: Outputs
CREATE TABLE  IF NOT EXISTS Outputs (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_Product INT,
    Quantity INT,
    Output_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Recipient VARCHAR(150),
    FOREIGN KEY (ID_Product) REFERENCES Products(ID) ON DELETE CASCADE
);

-- Tabela: Employees
CREATE TABLE IF NOT EXISTS Employees (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(150) NOT NULL,
    Position VARCHAR(100),
    Login VARCHAR(200),
    Password VARCHAR(200)
);

-- Tabela: Reports
CREATE TABLE IF NOT EXISTS Reports (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Type ENUM('Entrada', 'Saída'),
    Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    Details TEXT
);


