CREATE TYPE Role AS ENUM (
    'admin',
    'user'
    );

CREATE TABLE "user"
(
    "id"            bigint PRIMARY KEY,
    "username"      varchar,
    "password_hash" varchar,
    "email"         varchar,
    "role"          Role,
    "created_at"    timestamp
);

CREATE TABLE "grammatical_category"
(
    "id"          bigint PRIMARY KEY,
    "name"        varchar,
    "is_approved" bool
);

CREATE TABLE "term"
(
    "id"                      bigint PRIMARY KEY,
    "author_id"               integer,
    "grammatical_category_id" integer,
    "language_id"             integer,
    "name"                    varchar,
    "defintion"               text,
    "upvote_count"            integer,
    "downvote_count"          integer,
    "kuma_score"              varchar,
    "is_approved"             bool,
    "created_at"              timestamp
);

CREATE TABLE "tag"
(
    "id"          bigint PRIMARY KEY,
    "is_approved" bool,
    "name"        varchar
);

CREATE TABLE "tag_term"
(
    "id"      bigint PRIMARY KEY,
    "tag_id"  integer,
    "term_id" integer
);

CREATE TABLE "term_comment"
(
    "id"        bigint PRIMARY KEY,
    "author_id" integer,
    "term_id"   integer,
    "body"      text
);

CREATE TABLE "language"
(
    "id"          bigint PRIMARY KEY,
    "name"        text,
    "code"        varchar,
    "is_approved" bool
);

ALTER TABLE "term"
    ADD FOREIGN KEY ("author_id") REFERENCES "user" ("id");

ALTER TABLE "term"
    ADD FOREIGN KEY ("grammatical_category_id") REFERENCES "grammatical_category" ("id");

ALTER TABLE "term"
    ADD FOREIGN KEY ("language_id") REFERENCES "language" ("id");

ALTER TABLE "tag_term"
    ADD FOREIGN KEY ("tag_id") REFERENCES "tag" ("id");

ALTER TABLE "tag_term"
    ADD FOREIGN KEY ("term_id") REFERENCES "term" ("id");

ALTER TABLE "term_comment"
    ADD FOREIGN KEY ("author_id") REFERENCES "user" ("id");

ALTER TABLE "term_comment"
    ADD FOREIGN KEY ("term_id") REFERENCES "term" ("id");