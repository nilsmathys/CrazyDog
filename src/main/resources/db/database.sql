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
    pictureName      NVARCHAR(50)  NOT NULL,
    CONSTRAINT FK_Pieces_Coulour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID)

);
-- rot
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,3,'piece1red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,3,'piece2red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,3,'piece3red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,3,'piece4red.png');
--grün
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,4,'piece1green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,4,'piece2green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,4,'piece3green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,4,'piece4green.png');
-- gelb
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,5,'piece1yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,5,'piece2yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,5,'piece3yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,5,'piece4yellow.png');
-- blau
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,6,'piece1blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,6,'piece2blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,6,'piece3blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,6,'piece4blue.png');

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
    startimagename	NVARCHAR(40)	      NOT NULL,
    cssid 			NVARCHAR(40)	      NOT NULL,
    gamefieldNameID	int	      NOT NULL DEFAULT(1),
    colourID			int		  NOT NULL,
    CONSTRAINT FK_Gamefields_Colour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID),
    CONSTRAINT FK_Gamefields_GamefieldName FOREIGN KEY(gamefieldNameID)
        REFERENCES dbo.GamefieldName(gamefieldNameID)
);

--standard:1 wormhole:2 startfield:3 destinationfield:4 homefield:5
--white:1 black:2 red: 3 green:4 yellow:5 blue:6
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field1',3,4);--1 startfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field2',1,1);--2
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field3',1,1);--3
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field4',1,1);--4
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field5',1,1);--5
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field6',1,1);--6
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field7',1,1);--7
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field8',1,1);--8
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field9',2,2);--9 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field10',1,1);--10
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field11',1,1);--11
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field12',1,1);--12
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field13',1,1);--13
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field14',1,1);--14
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field15',1,1);--15
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field16',1,1);--16
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field17',3,5);--17 startfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field18',1,1);--18
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field19',1,1);--19
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field20',1,1);--20
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field21',1,1);--21
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field22',1,1);--22
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field23',1,1);--23
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field24',1,1);--24
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field25',2,2);--25 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field26',1,1);--26
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field27',1,1);--27
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field28',1,1);--28
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field29',1,1);--29
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field30',1,1);--30
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field31',1,1);--31
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field32',1,1);--32
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field33',3,4);--33 startfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field34',1,1);--34
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field35',1,1);--35
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field36',1,1);--36
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field37',1,1);--37
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field38',1,1);--38
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field39',1,1);--39
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field40',1,1);--40
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field41',2,2);--41 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field42',1,1);--42
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field43',1,1);--43
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field44',1,1);--44
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field45',1,1);--45
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field46',1,1);--46
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field47',1,1);--47
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field48',1,1);--48
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field49',3,6);--49 startfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field50',1,1);--50
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field51',1,1);--51
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field52',1,1);--52
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field53',1,1);--53
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field54',1,1);--54
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field55',1,1);--55
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field56',1,1);--56
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field57',2,2);--57 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field58',1,1);--58
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field59',1,1);--59
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field60',1,1);--60
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field61',1,1);--61
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field62',1,1);--62
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field63',1,1);--63
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field64',1,1);--64
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4red.png','field65',5,3);--65 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3red.png','field66',5,3);--66 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2red.png','field67',5,3);--67 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1red.png','field68',5,3);--68 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4yellow.png','field69',5,5);--69 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3yellow.png','field70',5,5);--70 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2yellow.png','field71',5,5);--71 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1yellow.png','field72',5,5);--72 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4green.png','field73',5,4);--73 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3green.png','field74',5,4);--74 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2green.png','field75',5,4);--75 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1green.png','field76',5,4);--76 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4blue.png','field77',5,6);--77 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3blue.png','field78',5,6);--78 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2blue.png','field79',5,6);--79 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1blue.png','field80',5,6);--80 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field81',4,4);--81 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field82',4,4);--82 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field83',4,4);--83 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field84',4,4);--84 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field85',4,6);--85 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field86',4,6);--86 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field87',4,6);--87 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field88',4,6);--88 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field89',4,3);--89 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field90',4,3);--90 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field91',4,3);--91 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field92',4,3);--92 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field93',4,5);--93 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field94',4,5);--94 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field95',4,5);--95 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field96',4,5);--96 destinationfield yellow---------------------------------------------------------------------
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
INSERT INTO dbo.Cards (name, value) VALUES ('oneEleven',11);
INSERT INTO dbo.Cards (name, value) VALUES ('standard',12);
INSERT INTO dbo.Cards (name, value) VALUES ('thirteen',13);
INSERT INTO dbo.Cards (name, value) VALUES ('questionmark',14);
INSERT INTO dbo.Cards (name, value) VALUES ('pieceExchange',15);

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
    pictureName      NVARCHAR(50)  NOT NULL,
    CONSTRAINT FK_Pieces_Coulour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID)

);
-- rot
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,3,'piece1red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,3,'piece2red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,3,'piece3red.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,3,'piece4red.png');
--grün
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,4,'piece1green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,4,'piece2green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,4,'piece3green.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,4,'piece4green.png');
-- gelb
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,5,'piece1yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,5,'piece2yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,5,'piece3yellow.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,5,'piece4yellow.png');
-- blau
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (1,6,'piece1blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (2,6,'piece2blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (3,6,'piece3blue.png');
INSERT INTO dbo.Pieces (number, colourID, pictureName) VALUES (4,6,'piece4blue.png');

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
    startimagename	NVARCHAR(40)	      NOT NULL,
    cssid 			NVARCHAR(40)	      NOT NULL,
    gamefieldNameID	int	      NOT NULL DEFAULT(1),
    colourID			int		  NOT NULL,
    CONSTRAINT FK_Gamefields_Colour FOREIGN KEY(colourID)
        REFERENCES dbo.Colour(colourID),
    CONSTRAINT FK_Gamefields_GamefieldName FOREIGN KEY(gamefieldNameID)
        REFERENCES dbo.GamefieldName(gamefieldNameID)
);

--standard:1 wormhole:2 startfield:3 destinationfield:4 homefield:5
--white:1 black:2 red: 3 green:4 yellow:5 blue:6
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field1',3,4);--1 startfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field2',1,1);--2
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field3',1,1);--3
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field4',1,1);--4
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field5',1,1);--5
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field6',1,1);--6
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field7',1,1);--7
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field8',1,1);--8
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field9',2,2);--9 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field10',1,1);--10
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field11',1,1);--11
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field12',1,1);--12
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field13',1,1);--13
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field14',1,1);--14
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field15',1,1);--15
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field16',1,1);--16
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field17',3,5);--17 startfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field18',1,1);--18
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field19',1,1);--19
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field20',1,1);--20
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field21',1,1);--21
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field22',1,1);--22
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field23',1,1);--23
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field24',1,1);--24
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field25',2,2);--25 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field26',1,1);--26
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field27',1,1);--27
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field28',1,1);--28
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field29',1,1);--29
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field30',1,1);--30
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field31',1,1);--31
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field32',1,1);--32
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field33',3,4);--33 startfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field34',1,1);--34
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field35',1,1);--35
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field36',1,1);--36
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field37',1,1);--37
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field38',1,1);--38
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field39',1,1);--39
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field40',1,1);--40
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field41',2,2);--41 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field42',1,1);--42
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field43',1,1);--43
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field44',1,1);--44
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field45',1,1);--45
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field46',1,1);--46
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field47',1,1);--47
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field48',1,1);--48
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field49',3,6);--49 startfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field50',1,1);--50
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field51',1,1);--51
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field52',1,1);--52
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field53',1,1);--53
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field54',1,1);--54
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field55',1,1);--55
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field56',1,1);--56
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field57',2,2);--57 wormhole
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field58',1,1);--58
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field59',1,1);--59
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field60',1,1);--60
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field61',1,1);--61
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field62',1,1);--62
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field63',1,1);--63
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field64',1,1);--64
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4red.png','field65',5,3);--65 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3red.png','field66',5,3);--66 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2red.png','field67',5,3);--67 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1red.png','field68',5,3);--68 homefield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4yellow.png','field69',5,5);--69 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3yellow.png','field70',5,5);--70 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2yellow.png','field71',5,5);--71 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1yellow.png','field72',5,5);--72 homefield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4green.png','field73',5,4);--73 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3green.png','field74',5,4);--74 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2green.png','field75',5,4);--75 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1green.png','field76',5,4);--76 homefield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece4blue.png','field77',5,6);--77 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece3blue.png','field78',5,6);--78 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece2blue.png','field79',5,6);--79 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('piece1blue.png','field80',5,6);--80 homefield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field81',4,4);--81 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field82',4,4);--82 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field83',4,4);--83 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field84',4,4);--84 destinationfield green
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field85',4,6);--85 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field86',4,6);--86 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field87',4,6);--87 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field88',4,6);--88 destinationfield blue
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field89',4,3);--89 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field90',4,3);--90 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field91',4,3);--91 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field92',4,3);--92 destinationfield red
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field93',4,5);--93 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field94',4,5);--94 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field95',4,5);--95 destinationfield yellow
INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES ('empty.png','field96',4,5);--96 destinationfield yellow