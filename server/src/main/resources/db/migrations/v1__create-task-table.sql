CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tasks (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    limit_date TIMESTAMP NOT NULL,
    done BOOLEAN NOT NULL DEFAULT(false)
);