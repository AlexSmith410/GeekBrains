BEGIN;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(255)
);

INSERT INTO users (name) VALUES ('Alex'),
                                ('Ivan'),
                                ('Kolya'),
                                ('Alik'),
                                ('Rufan'),
                                ('Sergey'),
                                ('Michael'),
                                ('Valera');

DROP TABLE IF EXISTS lots CASCADE;

CREATE TABLE lots (id BIGSERIAL PRIMARY KEY,
                   name VARCHAR(255),
                   current_bet INTEGER,
                   last_owner INTEGER REFERENCES users (id),
                   version INTEGER
);

INSERT INTO lots (name, current_bet, version) VALUES ('painting', 0, 0),
                                                     ('ring', 0, 0),
                                                     ('greek_coin', 0, 0),
                                                     ('potato', 0, 0);

COMMIT;