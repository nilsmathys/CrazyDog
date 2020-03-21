---------------------------------------------------------------------
-- Datenbank erzeugen
---------------------------------------------------------------------

USE master;

-- Falls vorhanden: DB löschen
IF DB_ID(N'CrazyDog') IS NOT NULL DROP DATABASE DAB2Perf;

-- Falls noch aktive Verbindungen vorhanden: Abbruch
IF @@ERROR = 3702
    RAISERROR(N'Datenbank CrazyDog kann nicht gelöscht werden da es noch aktive Verbindungen gibt.', 127, 127) WITH NOWAIT, LOG;

-- Datenbank erzeugen mit Standardoptionen
CREATE DATABASE CrazyDog;
GO

USE CrazyDog;
GO

---------------------------------------------------------------------
-- Tabellen erstellen
---------------------------------------------------------------------
-- Tabelle für die verschiedenen Karten
CREATE TABLE dbo.Cards
(
    cardID          INT          NOT NULL IDENTITY(2,1) PRIMARY KEY, --Primary key Auto Increment starts at to and increments by 1 every time
    name            NVARCHAR(50) NOT NULL,
    value           int
);

INSERT INTO dbo.Cards (name, value) VALUES ('standard',2);
INSERT INTO dbo.Cards (name, value) VALUES ('changeDirection',3);
INSERT INTO dbo.Cards (name, value) VALUES ('four',4);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',5);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',6);
INSERT INTO dbo.Cards (name, value) VALUES ('seven',7);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',8);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',9);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',10);
INSERT INTO dbo.Cards (name, value) VALUES ('oneEleven',NULL);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',12);
INSERT INTO dbo.Cards (name, value) VALUES ('thirteen',13);
INSERT INTO dbo.Cards (name, value) VALUES ('questionmark',NULL);
INSERT INTO dbo.Cards (name, value) VALUES ('pieceExchange',NULL);

-- Tabelle für die Spieler
CREATE TABLE dbo.Players
(
    playerID        INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    username        NVARCHAR(50) NOT NULL,
    email           NVARCHAR(50) NOT NULL,
    password		  NVARCHAR(50) NOT NULL
);

INSERT INTO dbo.Players (username, email, password) VALUES ('Spieler1','Spieler1@crazydog.ch','test123');
INSERT INTO dbo.Players (username, email, password) VALUES ('Spieler2','Spieler2@crazydog.ch','test123');
INSERT INTO dbo.Players (username, email, password) VALUES ('Spieler3','Spieler3@crazydog.ch','test123');
INSERT INTO dbo.Players (username, email, password) VALUES ('Spieler4','Spieler4@crazydog.ch','test123');

-- Tabelle für Farben
CREATE TABLE dbo.Colour
(
    coulourID      INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    colourname     NVARCHAR(50) NOT NULL DEFAULT('white')
);

INSERT INTO dbo.Colour (colourname) VALUES ('white'); --1
INSERT INTO dbo.Colour (colourname) VALUES ('black'); --2
INSERT INTO dbo.Colour (colourname) VALUES ('red'); --3
INSERT INTO dbo.Colour (colourname) VALUES ('green'); --4
INSERT INTO dbo.Colour (colourname) VALUES ('yellow'); --5
INSERT INTO dbo.Colour (colourname) VALUES ('blue'); --6

-- Tabelle für Spielfiguren
CREATE TABLE dbo.Pieces
(
    pieceID        INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    number         int          NOT NULL,
    coulourID      int          NOT NULL,
    CONSTRAINT FK_Gamefields_Coulour FOREIGN KEY(coulourID)
        REFERENCES dbo.Colour(coulourID)

);
-- rot
INSERT INTO dbo.Pieces (number, coulourID) VALUES (1,3);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (2,3);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (3,3);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (4,3);
--grün
INSERT INTO dbo.Pieces (number, coulourID) VALUES (1,4);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (2,4);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (3,4);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (4,5);
-- gelb
INSERT INTO dbo.Pieces (number, coulourID) VALUES (1,5);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (2,5);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (3,5);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (4,5);
-- blau
INSERT INTO dbo.Pieces (number, coulourID) VALUES (1,6);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (2,6);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (3,6);
INSERT INTO dbo.Pieces (number, coulourID) VALUES (4,6);

-- Tabelle für Spielfelder
CREATE TABLE dbo.Gamefields
(
    gamefieldID    INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    position_x     int	      NOT NULL,
    positon_y		 int		  NOT NULL,
    name           VARCHAR(30)  NOT NULL CHECK(name IN('standard','wormhole','startfield','destinationfield','homefield')) DEFAULT('standard'),
    coulourID		 int		  NOT NULL,
    CONSTRAINT FK_Gamefields_Coulour FOREIGN KEY(coulourID)
        REFERENCES dbo.Colour(coulourID)
);

