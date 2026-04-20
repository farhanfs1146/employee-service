-- Employee Info table and also will contain foreign keys or master data tables as well.
ALTER TABLE employees
    ADD COLUMN date_of_birth DATE;

ALTER TABLE employees
    ADD COLUMN email VARCHAR(150)