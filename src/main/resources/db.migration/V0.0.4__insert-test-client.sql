INSERT INTO clients(name, user_id, client_status_id)
VALUES ('ООО Ромашки и цветочки', 1, 1);

INSERT INTO contacts(phone_number, description, client_id)
VALUES ('375297654321', 'Рабочий - можно звонит с 8 до 17', 1),
       ('375295556555', 'Директор - Самоцветов В.В.', 1);

INSERT INTO calls(local_date_time, result, client_id)
VALUES ('2024-01-14', 'Нет ответа', 1),
       ('2024-01-15', 'Договорились о x', 1);

INSERT INTO meetings(local_date, result, client_id)
VALUES ('2024-01-15', 'Не договорились', 1),
       ('2024-01-16', 'Договорились', 1);
