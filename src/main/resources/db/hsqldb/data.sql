-- One admin user, named admin1 with passwor 4dm1n and authority admin
--INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
--INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
--INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- alepridur
--INSERT INTO users(username,password,enabled) VALUES ('ale','al3',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (4,'ale','owner');
-- alegalpel
--INSERT INTO users(username,password,enabled) VALUES ('alegalpel','Thealexan28',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (5,'alegalpel','owner');

-- Admins
INSERT INTO users(username,password,enabled) VALUES ('xtreme','xtr3m3',TRUE);
INSERT INTO admins(id, first_name, last_name, username, email) VALUES (1,'Parchis','Oca','xtreme','xtreme@xtreme.com');
INSERT INTO authorities(id,username,authority) VALUES (1,'xtreme','admin');

-- Players
INSERT INTO users(username,password,enabled) VALUES ('Hamil','abcd',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (1,'James','Carter','Hamil','aaasss@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (2,'Hamil','player');

INSERT INTO users(username,password,enabled) VALUES ('elon','musk',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (2,'Elon','Musk','elon','elon777@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (3,'elon','player');
