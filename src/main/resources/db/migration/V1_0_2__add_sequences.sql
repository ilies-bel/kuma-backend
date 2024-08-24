alter table "user"
    add column if not exists account_status varchar(64) not null default 'PENDING';

create sequence if not exists tag_seq start with 1 increment by 50;

create sequence if not exists term_seq start with 1 increment by 50;
