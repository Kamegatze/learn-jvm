begin;
create table if not exists icon(
   id uuid primary key,
   name text not null,
   image bytea not null
);

create table if not exists users(
    id uuid primary key,
    last_name text not null,
    first_name text not null,
    login text unique not null,
    password text not null,
    created_at timestamp not null,
    updated_at timestamp,
    last_authorization timestamp,
    icon_id uuid,
    constraint fk_users_on_icons foreign key(icon_id) references icon(id)
);

create table if not exists posts(
    id uuid primary key,
    user_id uuid not null,
    label text not null,
    created_at timestamp not null,
    updated_at timestamp,
    constraint fk_posts_on_users foreign key(user_id) references users(id)
);

create table if not exists table_of_contents(
    id uuid primary key,
    label text not null,
    content text not null,
    created_at timestamp not null,
    updated_at timestamp
);

create table if not exists posts_table_of_contents(
    id uuid primary key,
    posts_id uuid not null,
    table_of_contents_id uuid not null,
    ordering int not null,
    constraint fk_posts_table_of_contents_on_post foreign key(posts_id) references posts(id),
    constraint fk_posts_table_of_contents_on_table_of_contents foreign key(table_of_contents_id) references table_of_contents(id)
);
end;