alter table term
    add column if not exists translation varchar;


create table bookmark
(
    id      BIGINT
        constraint bookmark_pkey
            primary key,

    user_id BIGINT
        constraint fk_bookmark_user
            references people (id),

    term_id BIGINT
        constraint fk_bookmark_term
            references term (id)
);

create sequence bookmark_seq start 1 increment 50;