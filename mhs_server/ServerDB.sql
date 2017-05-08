DROP TABLE MISC IF EXISTS;
DROP TABLE TIMEZONE IF EXISTS;
DROP TABLE TIME IF EXISTS;
DROP TABLE NTP_SERVER IF EXISTS;

CREATE TABLE IF NOT EXISTS NTP_SERVER
(
	ID INTEGER IDENTITY PRIMARY KEY,
	NAME VARCHAR(255),
	HOST VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TIMEZONE
(
	ID INTEGER IDENTITY PRIMARY KEY,
	NAME VARCHAR(255),
	LINK VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TIME
(
	ID INTEGER IDENTITY PRIMARY KEY,
	UPDATED BOOLEAN DEFAULT FALSE NOT NULL,					-- Update flag
	FK_TIMEZONE INTEGER NOT NULL,							-- The timezone  
	FK_NTP_SERVER INTEGER NOT NULL,							-- The current NTP server
	FOREIGN KEY(FK_TIMEZONE) REFERENCES TIMEZONE(ID),
	FOREIGN KEY(FK_NTP_SERVER) REFERENCES NTP_SERVER(ID)
);

CREATE TABLE IF NOT EXISTS MISC
(
	ID INTEGER IDENTITY PRIMARY KEY,
	VERSION INTEGER DEFAULT 0								-- Version of the database
);

INSERT INTO TIMEZONE ("(GMT-11:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-10:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-09:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-08:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-07:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-06:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-05:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-04:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-03:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-02:00) ",""); 
INSERT INTO TIMEZONE ("(GMT-01:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+00:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+01:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+02:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+03:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+04:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+05:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+06:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+07:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+08:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+09:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+10:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+11:00) ",""); 
INSERT INTO TIMEZONE ("(GMT+12:00) ",""); 

