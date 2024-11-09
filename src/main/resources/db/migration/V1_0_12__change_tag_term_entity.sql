DROP TABLE tag_term;

CREATE TABLE tag_term
(
    term_id BIGINT NOT NULL REFERENCES term (id),
    tag_id  BIGINT NOT NULL REFERENCES tag (id),
    PRIMARY KEY (term_id, tag_id)
);