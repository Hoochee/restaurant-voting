INSERT INTO users (name, email, password, voted)
VALUES ('User', 'user@yandex.ru', '{noop}password', false),
       ('Admin', 'admin@gmail.com', '{noop}admin', false );

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (name)
VALUES ('A'),
       ('B'),
       ('C');

INSERT INTO dishes (name, price, on_date, restaurant_id)
VALUES ('овсянка', 100,'2020-01-30', 1),
       ('борщ', 200, '2020-01-30', 1),
       ('оливье', 150, '2020-01-30',1),
       ('гречка', 110, '2020-01-29', 2),
       ('суп-пюре', 210, '2020-01-29', 2),
       ('цезарь', 150, '2020-01-29',2),
       ('глазунья', 150, '2020-01-30', 3),
       ('гороховый суп', 230, '2020-01-30', 3),
       ('летний', 100, '2020-01-30', 3);


