CREATE TABLE IF NOT EXISTS loan_user (id BIGSERIAL NOT NULL,
                                      first_name VARCHAR PRIMARY KEY,
                                      credit_score INT NOT NULL);