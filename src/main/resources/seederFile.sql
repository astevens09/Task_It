use taskit_db;

insert into category(type)
values ('work'),
       ('chores'),
       ('academic'),
       ('finance'),
       ('health'),
       ('misc');

insert into user(email, first_name, last_name, password, username,profile_image)
value ('anthony@gmail.com','Anthony','Stevens','$2a$10$GsOi9SscCwtgCSgf0D1AVeIlEORlgr8AEsUHoeFLkvv2qg849ZdIy','astevens09','https://cdn.filestackcontent.com/SftfgsETQmEGDT0gfjsq')