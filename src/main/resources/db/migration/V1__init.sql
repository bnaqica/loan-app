GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ysg_user;
GRANT USAGE, UPDATE, SELECT ON ALL SEQUENCES IN SCHEMA public TO ysg_user;

CREATE TABLE IF NOT EXISTS loan_application (
id BIGSERIAL PRIMARY KEY,
current_loan_amount NUMERIC(10,2) NOT NULL,
first_name VARCHAR NOT NULL,
credit_score INT NOT NULL,
monthly_income NUMERIC(10,2) NOT NULL,
phone VARCHAR NOT NULL,
postal_code VARCHAR NOT NULL,
vehicle_make VARCHAR NOT NULL,
vehicle_mileage INT NOT NULL,
vehicle_model VARCHAR NOT NULL,
vehicle_year INT NOT NULL,
selected_loan_rate_id BIGINT
);

CREATE TABLE IF NOT EXISTS loan_application_rate (
id BIGSERIAL PRIMARY KEY,
loan_amount NUMERIC(10,2) NOT NULL,
loan_term INT NOT NULL,
interest_rate NUMERIC(3,2) NOT NULL,
application_id BIGINT REFERENCES loan_application(id)
);

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

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_credit_score, loan_rate)
VALUES (2019, 60, 781, 2.99);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 60, 780, 731, 3.19);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 60, 730, 681, 3.74);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 60, 680, 621, 5.99);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, min_credit_score, loan_rate)
VALUES (2019, 72, 61, 781, 3.64);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 72, 61, 780, 731, 3.84);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 72, 61, 730, 681, 4.39);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 72, 61, 680, 621, 6.49);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, min_credit_score, loan_rate)
VALUES (2019, 84, 73, 781, 4.24);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 84, 73, 780, 731, 4.44);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 84, 73, 730, 681, 5.29);

INSERT INTO loan_rate_sheet (min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2019, 84, 73, 680, 621, 7.54);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_credit_score, loan_rate)
VALUES (2018, 2015, 60, 781, 3.24);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 60, 780, 731, 3.44);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 60, 730, 681, 3.99);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 60, 680, 621, 6.24);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, min_credit_score, loan_rate)
VALUES (2018, 2015, 72, 61, 781, 3.89);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 72, 61, 780, 731, 4.09);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 72, 61, 730, 681, 4.64);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 72, 61, 680, 621, 7.09);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, min_credit_score, loan_rate)
VALUES (2018, 2015, 84, 73, 781, 4.59);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 84, 73, 780, 731, 4.94);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 84, 73, 730, 681, 5.79);

INSERT INTO loan_rate_sheet (max_vehicle_year, min_vehicle_year, max_loan_term, min_loan_term, max_credit_score, min_credit_score, loan_rate)
VALUES (2018, 2015, 84, 73, 680, 621, 8.19);
