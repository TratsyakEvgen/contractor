CREATE TABLE account_types
(
    id        BIGSERIAL,
    type_name VARCHAR(255),
    CONSTRAINT pk_account_types PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id              BIGSERIAL,
    login           VARCHAR(255),
    password        VARCHAR(255),
    account_type_id BIGINT,
    user_id         BIGINT,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE auth_data
(
    user_id  BIGINT NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    role_id  BIGINT,
    CONSTRAINT pk_authdata PRIMARY KEY (user_id)
);

CREATE TABLE calls
(
    id         BIGSERIAL,
    local_date date,
    result     VARCHAR(255),
    client_id  BIGINT,
    CONSTRAINT pk_calls PRIMARY KEY (id)
);

CREATE TABLE client_contacts
(
    id           BIGSERIAL,
    phone_number VARCHAR(255),
    description  VARCHAR(255),
    client_id    BIGINT,
    CONSTRAINT pk_client_contacts PRIMARY KEY (id)
);

CREATE TABLE client_status
(
    id     BIGSERIAL,
    status VARCHAR(255),
    CONSTRAINT pk_client_status PRIMARY KEY (id)
);

CREATE TABLE clients
(
    id               BIGSERIAL,
    name             VARCHAR(255),
    user_id          BIGINT,
    client_status_id BIGINT,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE meetings
(
    id         BIGSERIAL,
    local_date date,
    result     VARCHAR(255),
    report_id  BIGINT,
    client_id  BIGINT,
    CONSTRAINT pk_meetings PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                     BIGSERIAL,
    client_id              BIGINT,
    transportation_type_id BIGINT,
    transport_type         VARCHAR(255),
    loading_place          VARCHAR(255),
    loading_date           date,
    custom_departure       VARCHAR(255),
    uploading_place        VARCHAR(255),
    uploading_date         date,
    custom_destination     VARCHAR(255),
    code_tnvd              BIGINT,
    name                   VARCHAR(255),
    weight                 VARCHAR(255),
    cost                   DECIMAL,
    dimensions             VARCHAR(255),
    packing                VARCHAR(255),
    non_tariff_regulation  VARCHAR(255),
    stacking               BOOLEAN,
    temperature            VARCHAR(255),
    adr                    VARCHAR(255),
    client_rate            DECIMAL,
    info                   VARCHAR(255),
    client_full_name       VARCHAR(255),
    client_number_phone    VARCHAR(255),
    reward_id              BIGINT,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   BIGSERIAL,
    role VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE transportation_types
(
    id   BIGSERIAL,
    type VARCHAR(255),
    CONSTRAINT pk_transportation_types PRIMARY KEY (id)
);

CREATE TABLE user_info
(
    user_id              BIGINT NOT NULL,
    telegram_id          BIGINT,
    registration_address VARCHAR(255),
    real_address         VARCHAR(255),
    passport_number      VARCHAR(255),
    passport_id          VARCHAR(255),
    insurance_id         VARCHAR(255),
    phone_number         VARCHAR(255),
    email                VARCHAR(255),
    CONSTRAINT pk_userinfo PRIMARY KEY (user_id)
);

CREATE TABLE reports
(
    id        BIGSERIAL,
    date      date,
    path      VARCHAR(255),
    file_name VARCHAR(255),
    CONSTRAINT pk_reports PRIMARY KEY (id)
);

CREATE TABLE users
(
    id        BIGSERIAL,
    full_name VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
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

ALTER TABLE auth_data
    ADD CONSTRAINT FK_AUTHDATA_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE auth_data
    ADD CONSTRAINT FK_AUTHDATA_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE calls
    ADD CONSTRAINT FK_CALLS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE clients
    ADD CONSTRAINT FK_CLIENTS_ON_CLIENT_STATUS FOREIGN KEY (client_status_id) REFERENCES client_status (id);

ALTER TABLE clients
    ADD CONSTRAINT FK_CLIENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE client_contacts
    ADD CONSTRAINT FK_CLIENT_CONTACTS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE meetings
    ADD CONSTRAINT FK_MEETINGS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_TRANSPORTATION_TYPE FOREIGN KEY (transportation_type_id) REFERENCES transportation_types (id);

ALTER TABLE user_info
    ADD CONSTRAINT FK_USERINFO_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE meetings
    ADD CONSTRAINT FK_MEETINGS_ON_REPORTS FOREIGN KEY (report_id) REFERENCES reports (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_REWARDS FOREIGN KEY (reward_id) REFERENCES rewards (id);