use riddlerundown;

-- Select statements
select * from team;
select * from positionplayers;
select * from pitchers;

-- Inserting new teams
insert into Team(teamName, location) values 
	('Athletics','Oakland');

-- Inserting new position players
insert into PositionPlayers(teamName, location, name, num, pos) values 
	('Athletics','Oakland','Seth Brown','15','Firt Base');
	
-- Inserting new pitchers
insert into pitchers (teamName, location, name, num) values 
	('Athletics','Oakland','Trevor May','65');
	

-- Delete teams
select * from team;
select * from positionplayers;
select * from pitchers;
set autocommit = off;
start transaction;
	delete from team where teamName='Athletics' and location='Oakland';
	delete from positionplayers where teamName='Athletics' and location='Oakland';
	delete from pitchers where teamName='Athletics' and location='Oakland';
commit;
set autocommit = on;

-- Delete position player
select * from positionplayers;
set autocommit = off;
start transaction;
	delete from positionplayers where teamName='Athletics' and location='Oakland' and num='15';
commit; 

-- Delete pitcher
select * from pitchers;
set autocommit = off;
start transaction;
	delete from pitchers where teamname='Athletics' and location='Oakland' and num='65';
commit; 

-- Update stats for position players
select * from positionplayers;
set autocommit = off;
start transaction;
	update positionplayers
	set games = '150', plateAppearances = '555', AtBats = '500', Runs = '55', Hits = '115', 2b = '26', 
		3b = '3', HR = '25', rbis = '73', walks = '51', strikeouts = '146', errors = '8'
	where teamname='Athletics' and location='Oakland' and num='15';
commit;

-- Update stats for pitchers
select * from pitchers;
set autocommit = off;
	update pitchers 
	set wins = '2', losses = '0', games = '26', gamesStarted = '0', inningsPitched = '25.0', completeGames = '0', shutouts = '0', saves = '1',
		runs = '14', earnedRuns = '14', hits = '27', homeruns = '4', walks = '9', hitByPitches = '0', strikeouts = '30', battersFaced = '111'
	where teamname='Athletics' and location='Oakland' and num='65';
commit;

-- Update stats for teams
select * from team;
set autocommit = off;
	update team 
	set wins = '60', losses = '102'
	where teamname='Athletics' and location='Oakland';
commit;















