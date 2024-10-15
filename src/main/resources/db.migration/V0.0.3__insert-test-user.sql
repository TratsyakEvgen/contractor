INSERT INTO users(id, full_name)
VALUES (1, 'Иванов Иван Иванович');

INSERT INTO auth_data(user_id, login, password, role_id)
VALUES (1, '123456789', '$2a$10$MCFA9EeSP5tfY33QXR69x.if9jiTRoqvu2SqXaf9zTaXZMIRGtwc6', 2);

INSERT INTO user_info(user_id, telegram_id, registration_address, real_address, passport_number, passport_id,
                      insurance_id, phone_number, email)
VALUES (1, '123456789', 'г.Гродно 13-переулок Светлый д.13 кв.13', 'г.Гродно улица Темная д.13 кв.13',
        'KH1234567', '0123456A123PB0', '0123456A123PB0', '+375291234567', 'vanya@gmail.com');

INSERT INTO accounts(login, password, account_type_id, user_id)
VALUES ('sip_login', 'sip123456', 1, 1),
       ('zoom_login', 'zoom123456', 2, 1);

