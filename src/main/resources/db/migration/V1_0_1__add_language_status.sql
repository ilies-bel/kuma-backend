alter table language
    add column approval_status varchar(64) not null default 'PENDING';

alter table language
    drop column is_approved;

alter table tag
    add column approval_status varchar(64) not null default 'PENDING';

alter table tag
    drop column is_approved;

alter table grammatical_category
    add column approval_status varchar(64) not null default 'PENDING';

alter table grammatical_category
    drop column is_approved;

alter table term
    add column approval_status varchar(64) not null default 'PENDING';

alter table term
    drop column is_approved;

