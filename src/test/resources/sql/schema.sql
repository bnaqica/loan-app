CREATE TABLE IF NOT EXISTS loan_rate_sheet (
                                               id SERIAL PRIMARY KEY NOT NULL,
                                               max_vehicle_year INT,
                                               min_vehicle_year INT,
                                               max_loan_term INT,
                                               min_loan_term INT,
                                               max_credit_score INT,
                                               min_credit_score INT,
                                               loan_rate NUMERIC(3, 2) NOT NULL
);