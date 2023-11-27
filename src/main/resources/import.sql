

/* Populate table products */
INSERT INTO products (id, name, price) VALUES(1, 'Oil', 259990);
INSERT INTO products (id, name, price) VALUES(2, 'lbreak', 12345);
INSERT INTO products (id, name, price) VALUES(3, 'rbreak', 1499990, NOW());
INSERT INTO products (id, name, price) VALUES(4, 'petrol tank', 3799);
INSERT INTO products (id, name, price) VALUES(5, 'cng', 69990);
INSERT INTO products (id, name, price) VALUES(6, 'airbag', 6999);
INSERT INTO products (id, name, price) VALUES(7, 'engine', 29999);
INSERT INTO products (id, name, price) VALUES(8, 'battery', 29965);

/* Populate table labour */
INSERT INTO labour (id, name, price) VALUES(1, 'ram', 250);
INSERT INTO labour (id, name, price) VALUES(2, 'sham', 190);
INSERT INTO labour (id, name, price) VALUES(3, 'manish', 140);
INSERT INTO labour (id, name, price) VALUES(4, 'suresh', 370);
INSERT INTO labour (id, name, price) VALUES(5, 'vishal', 690);
INSERT INTO labour (id, name, price) VALUES(6, 'neha', 60);
INSERT INTO labour (id, name, price) VALUES(7, 'raj',77);
INSERT INTO labour (id, name, price) VALUES(8, 'shoumit',67);



/* Invoices */
INSERT INTO invoices(id, description, observation, client_id, create_at) VALUES(1, 'Invoice office team', null, 1, NOW());
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 1); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(2, 1, 4); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 5); 
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(1, 1, 7); 

INSERT INTO invoices(id, description, observation, client_id, create_at) VALUES(2, 'Bike Invoice', 'IMPORTANT STUFF', 1, NOW());
INSERT INTO items_invoices(amount, invoice_id, product_id) VALUES(3, 2, 6); 

/* */
INSERT INTO users (id, username, password, enabled) VALUES (1, 'rodri', '$2a$10$bwhu5TxyJPuxwn6.g2bUC.8dUCV5vh9eq40RoFX4pEIDqrHlEUx3.', 1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'admin', '$2a$10$R244P6hZ4MUa9EBeAUEcne5B8Lb6mTzw5.2vzKH6S7q9u3pqrGfoW', 1);

INSERT INTO authorities (user_id, authority) VALUES ('1', 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES ('2', 'ROLE_USER');
