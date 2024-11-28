INSERT INTO users(full_name, login, password, role_id)
VALUES ('Иванов Иван Иванович', '123', '$2a$10$MCFA9EeSP5tfY33QXR69x.if9jiTRoqvu2SqXaf9zTaXZMIRGtwc6', 2);

INSERT INTO accounts(login, password, account_type_id, user_id)
VALUES ('sip_login', 'sip123456', 1, 1),
       ('zoom_login', 'zoom123456', 2, 1);

