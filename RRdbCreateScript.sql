
-- ------------------------------------------------
-- Resets database if it already exists
DROP DATABASE IF EXISTS riddlerundown;
CREATE DATABASE IF NOT EXISTS riddlerundown;

use riddlerundown;
-- ------------------------------------------------
create table Team(
	teamName VARCHAR(20),
	location VARCHAR(40),
	wins INTEGER,
	losses INTEGER,
	
	primary key(teamName,location)
);

create table PositionPlayers(
	teamName VARCHAR(20),  
	location VARCHAR(20),  
	name VARCHAR(30),  
	num INTEGER, 
	pos VARCHAR(20),  
	games INTEGER,
	plateAppearances INTEGER,  
	AtBats INTEGER, 
	Runs INTEGER,
	Hits INTEGER,    
	2b INTEGER,  
	3b INTEGER,  
	HR INTEGER,  
	rbis INTEGER,  
	walks INTEGER,
	strikeouts INTEGER,
	errors INTEGER,
	
	primary key(num),
	foreign key(teamName,location) references Team(teamName,location)
);

create table Pitchers(
	teamName VARCHAR(20),  
	location VARCHAR(20),  
	name VARCHAR(30),  
	num INTEGER, 
	wins INTEGER,
	losses INTEGER,
	games INTEGER,
	gamesStarted INTEGER,
	inningsPitched DECIMAL(4,1),  
	completeGames INTEGER,  
	shutouts INTEGER,  
	saves INTEGER,  
	runs INTEGER,  
	earnedRuns INTEGER,  
	hits INTEGER,  
	homeruns INTEGER,  
	walks INTEGER,  
	hitByPitches INTEGER,  
	strikeouts INTEGER,  
	battersFaced INTEGER,
	
	primary key(num),
	foreign key(teamName,location) references Team(teamName,location)
);


-- ------------------------------------------------
-- Insert Data
insert into Team values
	('Guardians','Cleveland', 92,70),
	('Mets','New York',101,61),
	('Giants','San Fransisco',81,81),
	('Mariners','Seattle',90,72);

insert into PositionPlayers values 
	('Guardians','Cleveland','Steven Kwan',38,'Left Field',147,638,563,89,168,25,7,6,52,62,60,4),
	('Mets','New York','Fransisco Lindor',12,'Short Stop',161,706,630,98,170,25,5,26,107,59,133,9),
	('Giants','San Fransisco','Brandon Crawford',35,'Short Stop',118,458,407,50,94,15,2,9,52,39,98,16),
	('Mariners','Seattle','Julio Rodriguez',44,'Center Field',132,560,511,84,145,25,3,28,75,40,145,6);

insert into Pitchers values
	('Guardians','Cleveland','Emmanuel Clase',48,3,4,77,0,72.2,0,0,42,18,11,43,3,10,1,77,271),
	('Mets','New York','Edwin Diaz',39,3,1,61,0,62.0,0,0,32,9,9,34,3,18,2,118,235),
	('Giants','San Fransisco','Logan Webb',62,15,9,32,32,192.1,0,0,0,76,62,174,11,49,7,163,787),
	('Mariners','Seattle','Luis Castillo',58,8,6,25,25,150.1,0,0,0,56,50,118,13,45,8,167,615);

-- ------------------------------------------------
-- Table select statements

select * from team;
select * from positionplayers;
select * from pitchers;

-- ------------------------------------------------




