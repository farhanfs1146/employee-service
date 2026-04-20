-- employee card number in employees table.
ALTER TABLE employees
    ADD COLUMN card_number BIGINT NOT NULL UNIQUE;