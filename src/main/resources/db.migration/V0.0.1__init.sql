CREATE TABLE account_types
(
    id   BIGSERIAL,
    type TEXT NOT NULL,
    CONSTRAINT pk_account_types PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id              BIGSERIAL,
    login           TEXT   NOT NULL,
    password        TEXT   NOT NULL,
    account_type_id BIGINT NOT NULL,
    user_id         BIGINT NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE calls
(
    id              BIGSERIAL,
    local_date_time TIMESTAMP NOT NULL,
    result          TEXT      NOT NULL,
    client_id       BIGINT    NOT NULL,
    CONSTRAINT pk_calls PRIMARY KEY (id)
);

CREATE TABLE contacts
(
    id           BIGSERIAL,
    phone_number VARCHAR(12) NOT NULL,
    description  TEXT        NOT NULL,
    client_id    BIGINT      NOT NULL,
    CONSTRAINT pk_client_contacts PRIMARY KEY (id)
);

CREATE TABLE client_status
(
    id     BIGSERIAL,
    status TEXT NOT NULL,
    CONSTRAINT pk_client_status PRIMARY KEY (id)
);

CREATE TABLE clients
(
    id               BIGSERIAL,
    name             TEXT   NOT NULL,
    user_id          BIGINT NOT NULL,
    client_status_id BIGINT NOT NULL,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE meetings
(
    id         BIGSERIAL,
    local_date DATE   NOT NULL,
    result     TEXT   NOT NULL,
    report_id  BIGINT,
    client_id  BIGINT NOT NULL,
    CONSTRAINT pk_meetings PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                    BIGSERIAL,
    client_id             BIGINT,
    loading_place         TEXT,
    uploading_place       TEXT,
    description           TEXT,
    loading_date          DATE,
    custom_departure      TEXT,
    uploading_date        date,
    custom_destination    TEXT,
    code_tnvd             TEXT,
    name                  TEXT,
    weight                TEXT,
    cost                  numeric(19, 2),
    dimensions            TEXT,
    packing               TEXT,
    non_tariff_regulation TEXT,
    stacking              BOOLEAN,
    temperature           TEXT,
    adr                   TEXT,
    client_rate           numeric(19, 2),
    info                  TEXT,
    client_full_name      TEXT        NOT NULL,
    client_number_phone   VARCHAR(12) NOT NULL,
    reward_id             BIGINT,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE users
(
    id        BIGSERIAL,
    full_name TEXT   NOT NULL,
    login     TEXT   NOT NULL,
    password  TEXT   NOT NULL,
    role_id   BIGINT NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id        BIGSERIAL,
    role_name TEXT NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE reports
(
    id        BIGSERIAL,
    file_name TEXT,
    CONSTRAINT pk_reports PRIMARY KEY (id)
);

CREATE TABLE rewards
(
    id     BIGSERIAL,
    amount numeric(19, 2),
    CONSTRAINT pk_rewards PRIMARY KEY (id)
);

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_ACCOUNT_TYPE FOREIGN KEY (account_type_id) REFERENCES account_types (id);

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE calls
    ADD CONSTRAINT FK_CALLS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE clients
    ADD CONSTRAINT FK_CLIENTS_ON_CLIENT_STATUS FOREIGN KEY (client_status_id) REFERENCES client_status (id);

ALTER TABLE clients
    ADD CONSTRAINT FK_CLIENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE contacts
    ADD CONSTRAINT FK_CONTACTS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE meetings
    ADD CONSTRAINT FK_MEETINGS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE meetings
    ADD CONSTRAINT FK_MEETINGS_ON_REPORTS FOREIGN KEY (report_id) REFERENCES reports (id) ON DELETE CASCADE;

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_REWARDS FOREIGN KEY (reward_id) REFERENCES rewards (id);

CREATE INDEX logins ON users (login);