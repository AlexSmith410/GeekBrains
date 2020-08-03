BEGIN;

DROP TABLE IF EXISTS consumers CASCADE;
create TABLE consumers (id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) UNIQUE
);

INSERT INTO consumers (name) VALUES ('Ivan'),
                                    ('Alik'),
                                    ('Rufan');

DROP TABLE IF EXISTS goods CASCADE;

create TABLE goods (id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(255) UNIQUE,
                    current_price INTEGER
);

INSERT INTO goods (name, current_price) VALUES ('bread', 20),
                                               ('milk', 60),
                                               ('eggs', 40),
                                               ('bacon', 100);

DROP TABLE IF EXISTS purchases CASCADE;

create TABLE purchases(id BIGSERIAL PRIMARY KEY,
                       cost INTEGER,
                       consumer_id  INTEGER REFERENCES consumers (id) ON DELETE CASCADE,
                       good_id INTEGER REFERENCES goods (id) ON DELETE CASCADE
);
INSERT INTO purchases (consumer_id, good_id, cost) VALUES (1, 1, 20),
                                                          (1, 2, 60),
                                                          (2, 3, 40),
                                                          (2, 4, 100),
                                                          (3, 1, 20),
                                                          (3, 3, 40);

COMMIT;