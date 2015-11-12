DELETE FROM client;
ALTER SEQUENCE client_id_seq RESTART WITH 1;

INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Ivan', 'Ivan', '+380638925578', '1@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Ivan', '+380638925678', '2@urk.net', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Petrov', 'Test', '+380688325678', '3@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Семенова', 'Анна', '+38098325678', '4@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Дмитрий', '+38050325678', '5@urk.net', 2);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Феофанов', 'Андрей', '+38050325678', '6@urk.net',3);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Сергеев', 'Дмитрий', '+38055325678', '2@mail.ru', 1);
INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES ('Наумова', 'Дмитрий', '+380738625020', 'naumov@mail.ru', 1);


