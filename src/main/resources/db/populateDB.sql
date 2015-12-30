DELETE FROM users;
DELETE FROM call_result;
DELETE FROM client;
DELETE FROM task;
DELETE FROM originate_param;
ALTER SEQUENCE client_id_seq RESTART WITH 1;
ALTER SEQUENCE task_id_seq RESTART WITH 1;
ALTER SEQUENCE originate_param_id_seq RESTART WITH 1;
ALTER SEQUENCE call_result_id_seq RESTART WITH 1;
ALTER SEQUENCE user_id_seq RESTART WITH 1;

INSERT INTO users (login, password_hash, role) VALUES ('admin', 'g8keeper', 'ROLE_ADMIN');
INSERT INTO users (login, password_hash, role) VALUES ('manager', 'g8keeper', 'ROLE_MANAGER');
INSERT INTO users (login, password_hash, role) VALUES ('user', 'g8keeper', 'ROLE_USER');


INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Ivan', 'Ivan', '+380638925578', '1@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Ivan', '+380638925678', '2@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Test', '+380688325678', '3@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Семенова', 'Анна', '+38098325678', '4@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Дмитрий', '+38050325678', '5@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Андрей', '+38050325678', '6@urk.net',3);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Сергеев', 'Дмитрий', '+38055325678', '2@mail.ru', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Наумова', 'Дмитрий', '+380738625020', 'naumov@mail.ru', 1);


INSERT INTO originate_param(name, context, exten, priority, async, timeout, var1, var2, trunk) VALUES ('from-ami', 'from-ami', 's', 1, FALSE , 30000, 'var1Value', 'var2Value', 'SIP/zadarma/');
INSERT INTO originate_param(name, context, exten, priority, async, timeout, var1, var2, trunk) VALUES ('outbount without handlers', 'from-admin', 's', 1, FALSE , 20000, 'test', 'test', 'SIP/zadarma/');

INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('vip', 1, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('debtors dialer', 2, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('new clients', 3, TRUE , 2);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('hold calls', 2, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('безнадежные звонки', 2, TRUE , 2);

INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-11-30 10:00:00', 'success', 'success', 1, 1);
INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-12-01 11:00:00', 'failed', 'no-answer', 1, 2);
INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-12-01 12:00:00', 'failed', 'no-answer', 1, 3);
INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-12-02 13:00:00', 'success', 'success', 1, 3);
INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-12-02 14:00:00', 'success', 'success', 2, 3);
INSERT INTO call_result(datetime, result, reason, task_id, client_id) VALUES ('2015-12-02 18:00:00', 'failed', 'busy', 2, 3);




