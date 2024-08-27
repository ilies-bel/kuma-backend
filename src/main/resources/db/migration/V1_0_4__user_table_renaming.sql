alter table people
    rename column username to name;

alter table people
    add column password varchar(255);