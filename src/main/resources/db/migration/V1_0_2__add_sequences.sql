alter table "user"
    add column if not exists account_status varchar(64) not null default 'PENDING';

create sequence if not exists tag_seq start with 1 increment by 50;

create sequence if not exists term_seq start with 1 increment by 50;

alter table term
    alter column language_id type bigint;


alter table term
    add column if not exists upvotes integer not null default 0;