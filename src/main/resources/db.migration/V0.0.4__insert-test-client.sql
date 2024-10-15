INSERT INTO clients(id, name, user_id, client_status_id)
VALUES (1, 'ООО Ромашки и цветочки', 1, 1);

INSERT INTO client_contacts(id, phone_number, description, client_id)
VALUES (1, '+375297654321', 'Рабочий - можно звонит с 8 до 17', 1),
       (2, '+375295556555', 'Директор - Самоцветов В.В.', 1);

INSERT INTO calls(id, local_date, result, client_id)
VALUES (1, '2024-01-14', 'Договорились о встрече', 1);

INSERT INTO reports(id, date, path, file_name)
VALUES (1, '2024-01-15', 'https://some_server/path', 'Отчет.doc');

INSERT INTO meetings(id, local_date, result, report_id, client_id)
VALUES (1, '2024-01-15', 'Не договорились', 1, 1);
