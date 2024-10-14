begin;
alter table if exists posts add if not exists published bool;
update posts set published = false where posts.published is null;
end;