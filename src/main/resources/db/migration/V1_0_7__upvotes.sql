create table vote
(
    id        BIGINT
        constraint upvote_pkey
            primary key,

    user_id   BIGINT
        constraint fk_upvote_user
            references people (id),

    term_id   BIGINT
        constraint fk_upvote_term
            references term (id),

    is_upvote BOOLEAN
);

create sequence vote_seq start 1 increment 50;