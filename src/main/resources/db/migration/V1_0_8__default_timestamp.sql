-- add default value to timestamp column

alter table kuma.people
    alter column created_at set default current_timestamp;

alter table kuma.people
    drop column password;