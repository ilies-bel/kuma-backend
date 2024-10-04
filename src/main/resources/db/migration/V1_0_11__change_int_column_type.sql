DO
$$
    DECLARE
        fk_name  text;
        fk_table text;
    BEGIN
        FOR fk_name, fk_table IN SELECT tc.constraint_name, tc.table_name
                                 FROM information_schema.table_constraints tc
                                          JOIN information_schema.constraint_column_usage ccu
                                               ON tc.constraint_name = ccu.constraint_name
                                 WHERE tc.constraint_type = 'FOREIGN KEY'
                                   AND ccu.table_name = 'term'
                                   AND ccu.column_name = 'id'
            LOOP
                EXECUTE 'ALTER TABLE ' || fk_table || ' DROP CONSTRAINT ' || fk_name;
            END LOOP;
    END
$$;

-- Drop the primary key constraint
ALTER TABLE term
    DROP CONSTRAINT term_pkey;

-- Alter the column type to bigint
ALTER TABLE term
    ALTER COLUMN id TYPE bigint;

-- Recreate the primary key constraint
ALTER TABLE term
    ADD CONSTRAINT term_pkey PRIMARY KEY (id);