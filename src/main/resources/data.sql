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

INSERT INTO dishes (name, price, restaurant_id)
VALUES ('овсянка', 100, 1),
       ('борщ', 200, 1),
       ('оливье', 150, 1),
       ('гречка', 110, 2),
       ('суп-пюре', 210, 2),
       ('цезарь', 150, 2),
       ('глазунья', 150, 3),
       ('гороховый суп', 230, 3),
       ('летний', 100, 3);

