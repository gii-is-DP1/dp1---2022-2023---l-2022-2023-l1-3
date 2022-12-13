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
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (1,'James','Carter','Hamil','aaasss@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdnLgVOTqJ1bMAiB3nUFTSK5p7SvctUcHF0g&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (2,'Hamil','player');

INSERT INTO users(username,password,enabled) VALUES ('elon','musk',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (2,'Elon','Musk','elon','elon777@gmail.com',TRUE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSCNjL_xJ_VGaNFfQQvtjzqHfbXQBMcTmMTw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (3,'elon','player');

INSERT INTO users(username,password,enabled) VALUES ('harry','maguire',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (3,'harry','maguire','harry','diablosRojos@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (4,'harry','player');

INSERT INTO users(username,password,enabled) VALUES ('xavi','xavineta',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (4,'Xavi','Hernandez','xavi','europaLeague@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (5,'xavi','player');

INSERT INTO users(username,password,enabled) VALUES ('juan','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (5,'Juan','Martinez','juan','prueba@gmail.com',TRUE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (6,'juan','player');

INSERT INTO users(username,password,enabled) VALUES ('user1','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (6,'User1','User_lastName1','user1','prueba1@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (7,'user1','player');

INSERT INTO users(username,password,enabled) VALUES ('user2','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (7,'User2','User_lastName2','user2','prueba2@gmail.com',TRUE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (8,'user2','player');

INSERT INTO users(username,password,enabled) VALUES ('user3','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (8,'User3','User_lastName3','user3','prueba3@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (9,'user3','player');

INSERT INTO users(username,password,enabled) VALUES ('user4','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (9,'User4','User_lastName4','user4','prueba4@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (10,'user4','player');

INSERT INTO users(username,password,enabled) VALUES ('user5','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (10,'User5','User_lastName5','user5','prueba5@gmail.com',TRUE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (11,'user5','player');

INSERT INTO users(username,password,enabled) VALUES ('user6','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (11,'User6','User_lastName6','user6','prueba6@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (12,'user6','player');

INSERT INTO users(username,password,enabled) VALUES ('user7','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (12,'User7','User_lastName7','user7','prueba7@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (13,'user7','player');

INSERT INTO users(username,password,enabled) VALUES ('user8','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (13,'User8','User_lastName8','user8','prueba8@gmail.com',TRUE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (14,'user8','player');

INSERT INTO users(username,password,enabled) VALUES ('user9','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (14,'User9','User_lastName9','user9','prueba9@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (15,'user9','player');

INSERT INTO users(username,password,enabled) VALUES ('user10','contraseña12345',TRUE);
INSERT INTO players(id, first_name, last_name, username, email,is_online,pic_profile) VALUES (15,'User10','User_lastName10','user10','prueba10@gmail.com',FALSE,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU');
INSERT INTO authorities(id,username,authority) VALUES (16,'user10','player');

INSERT INTO friendships (state,player1,player2) VALUES ('ACCEPTED',1,2); -- SE PONE EL PLAYER ID
INSERT INTO friendships (state,player1,player2) VALUES ('ACCEPTED',1,3);
INSERT INTO friendships (state,player1,player2) VALUES ('ACCEPTED',1,5);
INSERT INTO friendships (state,player1,player2) VALUES ('ACCEPTED',1,7);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',3,4);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',1,4);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',1,13);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',6,1);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',7,1);
INSERT INTO friendships (state,player1,player2) VALUES ('PENDING',10,1);


INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game,player_winner) VALUES
(1,3,'PARCHIS', 'Partida 1', 1, TRUE, null,'FINISHED',1);
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game,player_winner) VALUES
(2,2,'OCA', 'Partida 2', 2, TRUE, null,'FINISHED',1);
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game,player_winner) VALUES
(3,3,'PARCHIS', 'Partida 3', 10, TRUE, null,'FINISHED',10);
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(4,4,'OCA', 'Partida 4', 12, TRUE, null,'STARTED');
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(5,3,'PARCHIS', 'Partida 5', 13, TRUE, null,'STARTED');
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(6,3,'OCA', 'Partida 6', 5, FALSE, null,'WAITING_PLAYERS');
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(7,4,'OCA', 'Partida 7', 6, TRUE, null,'WAITING_PLAYERS');
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(8,4,'OCA', 'Partida 8', 8, TRUE, null,'WAITING_PLAYERS');
INSERT INTO game(id, num_players, type_game, game_name, creator_player, is_public, chat_id,state_game) VALUES
(9,2,'PARCHIS', 'Partida 9', 15, TRUE, null,'WAITING_PLAYERS');

INSERT INTO rel_games_players(game_id,player_id) VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,7),(2,2),(2,8),(3,1),
(3,10),(4,12),(4,11),(4,9),(5,5),(5,13),(6,12),(6,5),(7,6),(8,8),(8,10),(9,15),(9,14);

INSERT INTO invitations (player1,player2,game,type) VALUES (1,10,1,'GAME');


