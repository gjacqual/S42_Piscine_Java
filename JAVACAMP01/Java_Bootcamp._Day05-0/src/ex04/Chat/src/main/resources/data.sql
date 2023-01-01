INSERT INTO chat.users (login, password) VALUES ('max1', 'defpassword0');
INSERT INTO chat.users (login, password) VALUES ('ale0', 'defpassword1');
INSERT INTO chat.users (login, password) VALUES ('alex2', 'defpassword2');
INSERT INTO chat.users (login, password) VALUES ('alex3', 'defpassword3');
INSERT INTO chat.users (login, password) VALUES ('alex4', 'defpassword4');
INSERT INTO chat.users (login, password) VALUES ('alex5', 'defpassword5');
INSERT INTO chat.users (login, password) VALUES ('alex6', 'defpassword6');

INSERT INTO chat.chat_rooms (name, owner) VALUES ('Main', 1);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('Personal', 2);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('Alex', 3);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('Channel', 4);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('BlaBlaBla', 5);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('Other', 1);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('AdminChat', 1);
INSERT INTO chat.chat_rooms (name, owner) VALUES ('Room', 1);

INSERT INTO chat.messages (author, room, text, date_time) VALUES (1, 1, 'test', '2022-05-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (2, 2, 'another', '2022-05-26 02:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (3, 3, 'bla bla bla', '2022-05-26 01:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (4, 4, 'yes', '2022-05-26 04:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (5, 5, 'good', '2022-05-26 01:10:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (3, 6, 'it is amassing', '2022-04-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (6, 7, 'cool', '2022-03-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (7, 3, 'no bad', '2022-01-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (2, 4, 'no no no', '2022-02-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (3, 2, 'come on', '2022-05-26 03:13:01.000000');
INSERT INTO chat.messages (author, room, text, date_time) VALUES (5, 1, 'shut up', '2022-03-26 03:13:01.000000');



