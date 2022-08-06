DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.users, chat.chat_rooms, chat.messages;

CREATE TABLE IF NOT EXISTS chat.users (
    id SERIAL PRIMARY KEY,
    login text UNIQUE NOT NULL,
    password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chat_rooms (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    owner INTEGER REFERENCES chat.users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES chat.users(id),
    room INTEGER REFERENCES chat.chat_rooms(id),
    text TEXT,
    date_time TIMESTAMP
);
