begin;
alter table if exists posts add if not exists content text;
drop table if exists posts_table_of_contents;
drop table if exists table_of_contents;
end;