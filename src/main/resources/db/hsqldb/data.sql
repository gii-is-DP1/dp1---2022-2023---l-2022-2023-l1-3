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

INSERT INTO users(username,password,enabled) VALUES ('harry','maguire',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (3,'harry','maguire','harry','diablosRojos@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (4,'harry','player');

INSERT INTO users(username,password,enabled) VALUES ('xavi','xavineta',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (4,'Xavi','Hernandez','xavi','europaLeague@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (5,'xavi','player');

INSERT INTO users(username,password,enabled) VALUES ('juan','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (5,'Juan','Martinez','juan','prueba@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (6,'juan','player');

INSERT INTO users(username,password,enabled) VALUES ('user1','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (6,'User1','User_lastName1','user1','prueba1@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (7,'user1','player');

INSERT INTO users(username,password,enabled) VALUES ('user2','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (7,'User2','User_lastName2','user2','prueba2@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (8,'user2','player');

INSERT INTO users(username,password,enabled) VALUES ('user3','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (8,'User3','User_lastName3','user3','prueba3@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (9,'user3','player');

INSERT INTO users(username,password,enabled) VALUES ('user4','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (9,'User4','User_lastName4','user4','prueba4@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (10,'user4','player');

INSERT INTO users(username,password,enabled) VALUES ('user5','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (10,'User5','User_lastName5','user5','prueba5@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (11,'user5','player');

INSERT INTO users(username,password,enabled) VALUES ('user6','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (11,'User6','User_lastName6','user6','prueba6@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (12,'user6','player');

INSERT INTO users(username,password,enabled) VALUES ('user7','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (12,'User7','User_lastName7','user7','prueba7@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (13,'user7','player');

INSERT INTO users(username,password,enabled) VALUES ('user8','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (13,'User8','User_lastName8','user8','prueba8@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (14,'user8','player');

INSERT INTO users(username,password,enabled) VALUES ('user9','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (14,'User9','User_lastName9','user9','prueba9@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (15,'user9','player');

INSERT INTO users(username,password,enabled) VALUES ('user10','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email) VALUES (15,'User10','User_lastName10','user10','prueba10@gmail.com');
INSERT INTO authorities(id,username,authority) VALUES (16,'user10','player');

INSERT INTO friendships (state,player1,player2) VALUES ('ACCEPTED',1,2); -- SE PONE EL PLAYER ID
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',3,4);

INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id) VALUES
(1,3,'PARCHIS', 'Partida 1', 1, TRUE, null);

