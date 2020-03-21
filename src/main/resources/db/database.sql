---------------------------------------------------------------------
-- Datenbank erzeugen
---------------------------------------------------------------------

USE master;

-- Falls vorhanden: DB löschen
IF DB_ID(N'CrazyDog') IS NOT NULL DROP DATABASE CrazyDog;

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
    colourID      INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
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
    colourID      int          NOT NULL,
    CONSTRAINT FK_Pieces_Coulour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID)

);
-- rot
INSERT INTO dbo.Pieces (number, colourID) VALUES (1,3);
INSERT INTO dbo.Pieces (number, colourID) VALUES (2,3);
INSERT INTO dbo.Pieces (number, colourID) VALUES (3,3);
INSERT INTO dbo.Pieces (number, colourID) VALUES (4,3);
--grün
INSERT INTO dbo.Pieces (number, colourID) VALUES (1,4);
INSERT INTO dbo.Pieces (number, colourID) VALUES (2,4);
INSERT INTO dbo.Pieces (number, colourID) VALUES (3,4);
INSERT INTO dbo.Pieces (number, colourID) VALUES (4,5);
-- gelb
INSERT INTO dbo.Pieces (number, colourID) VALUES (1,5);
INSERT INTO dbo.Pieces (number, colourID) VALUES (2,5);
INSERT INTO dbo.Pieces (number, colourID) VALUES (3,5);
INSERT INTO dbo.Pieces (number, colourID) VALUES (4,5);
-- blau
INSERT INTO dbo.Pieces (number, colourID) VALUES (1,6);
INSERT INTO dbo.Pieces (number, colourID) VALUES (2,6);
INSERT INTO dbo.Pieces (number, colourID) VALUES (3,6);
INSERT INTO dbo.Pieces (number, colourID) VALUES (4,6);

-- Tabelle für Spielfeld Name
CREATE TABLE dbo.GamefieldName
(
    gamefieldNameID    INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    name			     VARCHAR(20)  NOT NULL
);

INSERT INTO dbo.GamefieldName (name) VALUES ('standard'); --1
INSERT INTO dbo.GamefieldName (name) VALUES ('wormhole'); --2
INSERT INTO dbo.GamefieldName (name) VALUES ('startfield'); --3
INSERT INTO dbo.GamefieldName (name) VALUES ('destinationfield'); --4
INSERT INTO dbo.GamefieldName (name) VALUES ('homefield'); --5


-- Tabelle für Spielfelder
CREATE TABLE dbo.Gamefields
(
    gamefieldID		INT          NOT NULL IDENTITY(1,1) PRIMARY KEY,
    position_x		int	      NOT NULL,
    positon_y			int		  NOT NULL,
    gamefieldNameID	int	      NOT NULL DEFAULT(1),
    colourID		 int		  NOT NULL,
    CONSTRAINT FK_Gamefields_Colour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID),
    CONSTRAINT FK_Gamefields_GamefieldName FOREIGN KEY(gamefieldNameID)
        REFERENCES dbo.GamefieldName(gamefieldNameID)
);

--standard:1 wormhole:2 startfield:3 destinationfield:4 homefield:5
--white:1 black:2 red: 3 green:4 yellow:5 blue:6
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,3,4);--1 startfield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--2
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--3
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--4
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--5
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--6
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--7
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--8
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,2,2);--9 wormhole
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--10
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--11
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--12
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--13
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--14
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--15
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--16
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,3,5);--17 startfield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--18
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--19
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--20
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--21
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--22
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--23
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--24
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,2,2);--25 wormhole
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--26
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--27
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--28
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--29
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--30
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--31
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--32
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,3,4);--33 startfield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--34
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--35
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--36
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--37
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--38
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--39
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--40
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,2,2);--41 wormhole
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--42
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--43
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--44
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--45
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--46
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--47
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--48
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,3,6);--49 startfield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--50
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--51
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--52
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--53
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--54
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--55
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--56
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,2,2);--57 wormhole
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--58
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--59
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--60
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--61
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--62
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--63
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,1,1);--64
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,3);--65 homefield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,3);--66 homefield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,3);--67 homefield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,3);--68 homefield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,5);--69 homefield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,5);--70 homefield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,5);--71 homefield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,5);--72 homefield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,4);--73 homefield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,4);--74 homefield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,4);--75 homefield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,4);--76 homefield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,6);--77 homefield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,6);--78 homefield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,6);--79 homefield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,5,6);--80 homefield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,4);--81 destinationfield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,4);--82 destinationfield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,4);--83 destinationfield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,4);--84 destinationfield green
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,6);--85 destinationfield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,6);--86 destinationfield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,6);--87 destinationfield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,6);--88 destinationfield blue
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,3);--89 destinationfield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,3);--90 destinationfield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,3);--91 destinationfield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,3);--92 destinationfield red
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,5);--93 destinationfield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,5);--94 destinationfield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,5);--95 destinationfield yellow
INSERT INTO dbo.Gamefields (position_x, positon_y, gamefieldNameID, colourID) VALUES (0,0,4,5);--96 destinationfield yellow