USE csec;
INSERT INTO ecom_user_profile (id, type) VALUES (1,'ROLE_ADMIN');
INSERT INTO ecom_user_profile (id, type) VALUES (2,'ROLE_USER');

INSERT INTO ecom_user (id, password, username) VALUES (1, '$2a$12$DsIT6XBqERMgULRoyhp0duomQR7t72gY6/mEYNj6DMsTtUbopf85S', 'maryJaneForevar');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (1, '342 Milan Ave', 'Norwalk', '2018-02-27', 'maryJane@foobar.com', 'Mary Jane', '/assets/sql/maryPFP.jpg', '(979)268-5892', 'Ohio', '44857', (SELECT id FROM ecom_user WHERE id=1));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (1, '8699113478488935', '6', '30', 'Mary Jane', '307', (SELECT id FROM ecom_user WHERE id=1));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=1), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (2, '$2a$12$n7OecM2JNaoHC.dmSu1fieXv32.g/gyFYNplCNMwZ0b97NBB0VlSK', 'american_born_300');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (2, '57 Eliot St #7', 'Natick', '2018-07-11', 'johnMichaelAmerican@gmail.com', 'John Michael', '/assets/sql/johnPFP.jpg', '(504)309-6360', 'Maine', '01760', (SELECT id FROM ecom_user WHERE id=2));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (2, '6011341279372678', '7', '29', 'John Michael', '047', (SELECT id FROM ecom_user WHERE id=2));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=2), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (3, '$2a$12$Jaf2j7RQ4fRAoZvAYy6rEeqQrQJvfY8B6LW0e5z5z3bdUeudZ6mHC', '@superCandleKid');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (3, '842 S Akers St', 'Visalia', '2020-11-08', 'uncrackable@foobar.com', 'Christopher DeRolo', '/assets/sql/chrisPFP.jpg', '(412)678-5544', 'California', '93277', (SELECT id FROM ecom_user WHERE id=3));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (3, '5517530208313982', '10', '30', 'Christopher DeRolo', '355', (SELECT id FROM ecom_user WHERE id=3));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=3), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (4, '$2a$12$duUVZyHjr6YLa5Mr90xNX.Sz0Vms2d9tUAvelTlkSt7GEtKiwkwhK', 'candle.lover.1965');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (4, '4968 50 St', 'Camrose', '2019-05-26', 'parker.karen.1965@foobar.com', 'Karen Parker', '/assets/sql/karenPFP.jpg', '(780)672-2889', 'Alberta', 'T4V 1R1', (SELECT id FROM ecom_user WHERE id=4));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (4, '6011450315005455', '10', '29', 'Harry Parker', '508', (SELECT id FROM ecom_user WHERE id=4));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=4), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (5, '$2a$12$3aMpl00ERHxWuhLbeaGUzO.YFvGOBF5wH/tziDRU5oegNhniiJj2y', 'computerScienceBrofish');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (5, '1831 6th St', 'Brookings', '2019-12-03', 'cs.brofish@earthlink.net', 'George Smith', '/assets/sql/georgePFP.jpg', '(605)692-2289', 'South Dakota', '57006', (SELECT id FROM ecom_user WHERE id=5));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (5, '4556625627296399', '11', '31', 'George Smith', '384', (SELECT id FROM ecom_user WHERE id=5));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=5), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (6, '$2a$12$DLedgbYDGwCDqfVB2M0cnuEW1SQxlIIQH1ycuSOdbZBmWWTBajagO', 'GrandmaCarolLovesCandles');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (6, '329 Warren Ave', 'East Providence', '2020-01-17', 'GrandmaCarolBell@yahoo.com', 'Carol Bellbird', '/assets/sql/carolPFP.png', '(401)438-4653', 'Rhode Island', '02914', (SELECT id FROM ecom_user WHERE id=6));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (6, '371904143373052', '11', '25', 'Carol Bellbird', '698', (SELECT id FROM ecom_user WHERE id=6));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=6), (SELECT id FROM ecom_user_profile WHERE id=2));

INSERT INTO ecom_user (id, password, username) VALUES (7, '$2a$12$x78D2xbC8opTQjHqyiyNoeykBgzU7ybNLxXSj3ljfLyzCprbJafVm', 'admin');
INSERT INTO ecom_user_info (id, address, city, create_date, email, name, picture, phone, state, zip, user_id) VALUES (7, '329 Warren Ave', 'Rochester', '2017-01-01', 'admin@foobar.com', 'Admin User', '/assets/sql/carolPFP.png', '(123)456-7890', 'New YOrK', '01234', (SELECT id FROM ecom_user WHERE id=7));
INSERT INTO ecom_payment (id, card_number, expiry_month, expiry_year, owner_name, sec_code, user_id) VALUES (7, '371904143373052', '11', '25', 'Carol Bellbird', '698', (SELECT id FROM ecom_user WHERE id=7));
INSERT INTO c_user_c_user_profile (user_id, user_profile_id) VALUES ((SELECT id FROM ecom_user WHERE id=7), (SELECT id FROM ecom_user_profile WHERE id=1));
