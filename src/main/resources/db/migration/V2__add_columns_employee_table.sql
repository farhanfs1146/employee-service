-- Employee Info table and also will contain foreign keys or master data tables as well.
ALTER TABLE employees
ADD COLUMN is_active BOOLEAN NOT NULL DEFAULT TRUE