CREATE SCHEMA challenge
    AUTHORIZATION postgres;

CREATE TABLE challenge.orders
(
    id SERIAL NOT NULL,
    status character varying(50) NOT NULL,
    customer_id character varying(100) NOT NULL,
    seller_id character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE challenge.orders_items
(
    id SERIAL NOT NULL,
    product_id character varying(100) NOT NULL,
    order_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT ORDER_ITEM_UK01 UNIQUE (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES challenge.orders(id)
);