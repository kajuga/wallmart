
CREATE DATABASE wallmart;

DROP TABLE wallmart.products;
DROP TABLE wallmart.category;
DROP TABLE wallmart.producer;



CREATE TABLE wallmart.category
(
    id SERIAL NOT NULL CONSTRAINT category_id PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE wallmart.producer
(
    id SERIAL NOT NULL CONSTRAINT producer_id PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE wallmart.products
(
  id SERIAL NOT NULL CONSTRAINT products_id PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description text default 'none',
  category_id INTEGER REFERENCES wallmart.category(id) NOT NULL,
  producer_id INTEGER REFERENCES wallmart.producer(id) NOT NULL,
  price NUMERIC NOT NULL
);

INSERT INTO wallmart.category (name) VALUES
('Technics'),
('Books'),
('Food');

INSERT INTO wallmart.producer (name) VALUES
('Sony'),
('Biblioglobus'),
('McDonalds inc');


INSERT INTO wallmart.products (name, description, category_id, producer_id, price) VALUES
('Big Burger', 'very fatable', 3, 3, 0.99),
('CD Walkman', 'casette audio player', 1, 1, 25.20),
('Holy Bible', 'book about Jesus', 2, 2, 7.40);
