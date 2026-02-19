-- Employee Info table and also will contain foreign keys or master data tables as well.
CREATE TABLE employees
(

    id          BIGSERIAL PRIMARY KEY,

    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,

    father_name VARCHAR(150) NOT NULL,
    mother_name VARCHAR(150) NOT NULL,

--     master data tables references
    qualification_id BIGINT NOT NULL,
--     grade_id BIGINT NOT NULL, // for now we are just learning and will test to Feign Client approach to use Master Data Service.

--     audit fields
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER,

    updated_at TIMESTAMP,
    updated_by INTEGER

);