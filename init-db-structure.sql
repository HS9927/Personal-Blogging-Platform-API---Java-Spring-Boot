CREATE TABLE blogs 
(
	id SERIAL PRIMARY KEY,
	tag_id INT NOT NULL,
	name VARCHAR(100) NULL,
	content TEXT NULL,
	pushlish_date DATE NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT NULL
);


