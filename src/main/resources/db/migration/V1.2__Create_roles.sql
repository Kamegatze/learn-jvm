create table if not exists roles(
    id uuid primary key,
    name text unique
);

alter table if exists users add if not exists role_id uuid;
alter table if exists users drop constraint if exists fk_role_id;
alter table if exists users add constraint fk_role_id foreign key (role_id) references roles(id);

insert into roles(id, name) select gen_random_uuid(), 'ROLE_USER'
                            where not exists(select id from roles where name = 'ROLE_USER');
insert into roles(id, name) select gen_random_uuid(), 'ROLE_ADMIN'
                            where not exists(select id from roles where name = 'ROLE_ADMIN');
