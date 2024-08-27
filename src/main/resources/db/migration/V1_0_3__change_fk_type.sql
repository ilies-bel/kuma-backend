alter table term
    alter column grammatical_category_id type bigint;

alter table term
    alter column author_id type bigint;

alter table if exists "user"
    rename to people;

alter table tag_term
    alter column term_id type bigint;

alter table tag_term
    alter column tag_id type bigint;

create sequence if not exists tag_term_seq start with 1 increment by 50;