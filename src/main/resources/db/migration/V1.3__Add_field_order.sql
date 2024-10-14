begin;
alter table if exists table_of_contents add if not exists orders integer;
alter table if exists posts_table_of_contents drop column if exists ordering;
end;