INSERT INTO clients(name, user_id, client_status_id)
VALUES ('ООО Ромашки и цветочки', 1, 1);

INSERT INTO client_contacts(phone_number, description, client_id)
VALUES ('+375297654321', 'Рабочий - можно звонит с 8 до 17', 1),
       ('+375295556555', 'Директор - Самоцветов В.В.', 1);

INSERT INTO calls(local_date, result, client_id)
VALUES ('2024-01-14', 'Договорились о встрече', 1);

INSERT INTO reports(date, path, file_name)
VALUES ('2024-01-15', 'https://some_server/path', 'Отчет.doc');

INSERT INTO meetings(local_date, result, report_id, client_id)
VALUES ('2024-01-15', 'Не договорились', 1, 1);
