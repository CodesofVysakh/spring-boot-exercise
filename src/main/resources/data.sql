-- Seed users
INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com');
INSERT INTO users (name, email) VALUES ('Bob', 'bob@example.com');
INSERT INTO users (name, email) VALUES ('Charlie', 'charlie@example.com');

-- Seed orders (user_id references the users above)
INSERT INTO orders (amount, user_id) VALUES (150.00, 1);
INSERT INTO orders (amount, user_id) VALUES (50.00, 2);
INSERT INTO orders (amount, user_id) VALUES (300.00, 3);