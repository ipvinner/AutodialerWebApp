DELETE FROM client;
DELETE FROM task;
DELETE FROM originate_param;
ALTER SEQUENCE client_id_seq RESTART WITH 1;
ALTER SEQUENCE task_id_seq RESTART WITH 1;
ALTER SEQUENCE originate_param_id_seq RESTART WITH 1;

INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Ivan', 'Ivan', '+380638925578', '1@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Ivan', '+380638925678', '2@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Test', '+380688325678', '3@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Семенова', 'Анна', '+38098325678', '4@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Дмитрий', '+38050325678', '5@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Андрей', '+38050325678', '6@urk.net',3);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Сергеев', 'Дмитрий', '+38055325678', '2@mail.ru', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Наумова', 'Дмитрий', '+380738625020', 'naumov@mail.ru', 1);


INSERT INTO originate_param(name, context, extension, priority, async, timeout, var1, var2) VALUES ('from-ami', 'from-ami', 's', 1, FALSE , 30000, 'var1Value', 'var2Value');
INSERT INTO originate_param(name, context, extension, priority, async, timeout, var1, var2) VALUES ('outbount without handlers', 'from-admin', 's', 1, FALSE , 20000, 'test', 'test');

INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('vip', 1, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('debtors dialer', 2, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('new clients', 3, TRUE , 2);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('hold calls', 2, TRUE , 1);
INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES ('безнадежные звонки', 2, TRUE , 2);





