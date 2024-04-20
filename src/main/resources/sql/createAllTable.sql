drop table writer;
create table if not exists vats.writer
(
    id        int not null auto_increment primary key,
    firstName varchar(25),
    lastName  varchar(20)
);

insert into writer(firstName, lastName) VALUE ('Alexander', 'Levchenko');
insert into writer(firstName, lastName) VALUE ('Benjamin', 'Grahman');
insert into writer(firstName, lastName) VALUE ('Robert', 'Kiyosaki');
insert into writer(firstName, lastName) VALUE ('George', 'Clayson');

drop table vats.post;
create table if not exists vats.post
(
    id      int         not null auto_increment primary key,
    content varchar(255),
    created datetime    not null,
    updated datetime    not null,
    status  varchar(25) not null
);

insert into post(content, created, updated, status)
    value ('History of survival', current_time, current_time, 'ACTIVE');
insert into post(content, created, updated, status)
    value ('Investment in shares', current_time, current_time, 'ACTIVE');
insert into post(content, created, updated, status)
    value ('Ability to see', current_time, current_time, 'ACTIVE');
insert into post(content, created, updated, status)
    value ('Financial management', current_time, current_time, 'ACTIVE');


drop table vats.label;
create table if not exists vats.label
(
    id   int         not null auto_increment primary key,
    name varchar(50) not null
);

insert into label(name) VALUE ('Blackout');
insert into label(name) VALUE ('Reasonable investor');
insert into label(name) VALUE ('Rich dad, poor dad');
insert into label(name) VALUE ('The richest man in Babylon');


drop table label_post;
create table if not exists label_post
(
    post_id int,
    foreign key (post_id) references post (id),
    label_id int,
    foreign key (label_id) references label (id) on delete set null
);

insert into label_post(post_id, label_id) VALUE (1, 1);
insert into label_post(post_id, label_id) VALUE (2, 2);
insert into label_post(post_id, label_id) VALUE (3, 3);
insert into label_post(post_id, label_id) VALUE (4, 4);

create table if not exists writer_post
(
    writer_id int,
    foreign key (writer_id) references writer (id),
    post_id int,
    foreign key (post_id) references post (id)
);

insert into writer_post(writer_id, post_id) VALUE (1, 1);
insert into writer_post(writer_id, post_id) VALUE (2, 2);
insert into writer_post(writer_id, post_id) VALUE (3, 3);
insert into writer_post(writer_id, post_id) VALUE (4, 4);