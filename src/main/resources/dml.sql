INSERT INTO usr (email, hash_password,name, id,gender,bio,date_of_birth,active)
values ('asd@asd.com','$2a$10$MoBhmAV5YpxzWiTWEZbJdO2O7dwWl6UPx1fwC6DA1vlZdD9SL4rYa','ASD','5f0c4ab3-7d2b-4dcc-b8d1-de05a0842ac8','m','МАЛАДОЙ КЕКЕР','2000-01-01 00:00:00.000000',true),
       ('keker@mail.ru','$2a$10$un/8R7iYM9rR61MgRYPWD.X4C8gbMMA0rG4aXKqC6fSu52eTcWVmS','KEKER','2736e840-2289-4735-a667-b03a5c68f88e','m','ИХИХИХИХ','2000-01-01 00:00:00.000000',true),
       ('jopper@sad.com','$2a$10$JqZgeUKC/0CpSm974F1IfOxZYQPrX.4Y.W6sm/ivcnBd1BTs17xDK','WHOPPER','3e99fcba-69d0-4946-b8aa-ec6c91015eb3','m','да да я','2000-01-01 00:00:00.000000',true),
       ('elena@gmail.com','$2a$10$X0c6NFEPZoR2Ea4.Zou8xOU4P4LGLqI/HWlvWsYm/lix.nvdssbJC','Elena','43e41d1e-6017-4bcb-adb8-275ce9dc0cf5','f','модель','2000-01-01 00:00:00.000000',true);

INSERT INTO user_pictures(user_id,pictures) VALUES
('5f0c4ab3-7d2b-4dcc-b8d1-de05a0842ac8','https://img.freepik.com/free-photo/young-man-going-art-gallery_23-2149709125.jpg?w=996&t=st=1686149921~exp=1686150521~hmac=524a91d66d11c4a0b146125cecc9955b46e2e97d8f75ca22c2d84647af826759'),
('2736e840-2289-4735-a667-b03a5c68f88e','https://img.freepik.com/free-photo/front-view-rich-man-holding-lottery-tickets_23-2149820192.jpg?w=996&t=st=1686168497~exp=1686169097~hmac=e2818e9bb71fc8aec0c45754a697b7e9034a36bd50190e9d2353156c8e01c7b1'),
('43e41d1e-6017-4bcb-adb8-275ce9dc0cf5','https://img.freepik.com/free-photo/side-view-woman-looking-painting_23-2149911889.jpg?w=996&t=st=1686168514~exp=1686169114~hmac=eb414ff9a0800fc629921923235a5d4db6c4102254869e02d8f50102673c46d5'),
('3e99fcba-69d0-4946-b8aa-ec6c91015eb3','https://img.freepik.com/free-photo/portrait-young-handsome-man-jean-shirt-smiling-looking-camera_176420-12082.jpg?w=996&t=st=1686168566~exp=1686169166~hmac=eab857feae808dbda22470b26147f5180e8c70c8db578428028ebe09a3fe7b31');

INSERT INTO chat VALUES ('c99d2bde-1cb6-4bfa-9a81-864f615cf2b6');

INSERT INTO usr_chats VALUES
                          ('5f0c4ab3-7d2b-4dcc-b8d1-de05a0842ac8','c99d2bde-1cb6-4bfa-9a81-864f615cf2b6'),
                          ('2736e840-2289-4735-a667-b03a5c68f88e','c99d2bde-1cb6-4bfa-9a81-864f615cf2b6');