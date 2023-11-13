CREATE TABLE IF NOT EXISTS socks
(
    id          SERIAL PRIMARY KEY,
    color       VARCHAR NOT NULL,
    cotton_part INT     NOT NULL CHECK (cotton_part >= 0 AND cotton_part <= 100),
    UNIQUE (color, cotton_part)
);

CREATE TABLE IF NOT EXISTS socks_warehouse
(
    id       SERIAL PRIMARY KEY,
    socks_id INT REFERENCES socks (id) UNIQUE NOT NULL,
    quantity INT                              NOT NULL CHECK (quantity >= 0)
);

CREATE TABLE IF NOT EXISTS socks_transaction
(
    id               SERIAL PRIMARY KEY,
    socks_id         INT REFERENCES socks (id) NOT NULL,
    operation_type   VARCHAR                   NOT NULL,
    quantity INT                              NOT NULL CHECK (quantity >= 0),
    transaction_date TIMESTAMP                 NOT NULL
);

