alter table people
    alter column Role type varchar(255) using Role::varchar(255);


create sequence if not exists people_seq start with 1 increment by 50;

