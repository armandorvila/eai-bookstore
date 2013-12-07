
-------------------------- DROP Sentences -------------------------

drop TABLE IF EXISTS bookstore.bookstore_id_generator_table cascade;
drop SEQUENCE IF EXISTS bookstore.bookstore_id_generator cascade;

drop TABLE IF EXISTS bookstore.WaitingList cascade;
drop TABLE IF EXISTS bookstore.Book cascade;
drop TABLE IF EXISTS bookstore.Customer cascade;
drop TABLE IF EXISTS bookstore.Invoice cascade;
drop TABLE IF EXISTS bookstore.Notification cascade;
drop TABLE IF EXISTS bookstore.Order cascade;
drop TABLE IF EXISTS bookstore.Shipment_Order cascade;
drop TABLE IF EXISTS bookstore.Shipment cascade;

drop SCHEMA IF EXISTS bookstore cascade;

-------------------------- CREATE Sentences -------------------------

CREATE SCHEMA bookstore;

--Sequence for ids (It changes from one dbms to another)
CREATE TABLE bookstore.bookstore_id_generator_table(i int); 
INSERT INTO bookstore.bookstore_id_generator_table values(100000);
CREATE SEQUENCE bookstore.bookstore_id_generator as integer;


CREATE TABLE bookstore."Book"
(
  id bigint NOT NULL,
  name character varying(250),
  description character varying(1000),
  isbn character varying(50),
  price bigint,
  stock bigint,
  CONSTRAINT "Book_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Book_isbn_key" UNIQUE (isbn )
);


CREATE TABLE bookstore."Customer"
(
  id bigint NOT NULL,
  name character varying(70),
  nif character(9),
  address character varying(200),
  mail character varying(200),
  debt double precision,
  CONSTRAINT "Customer_pkey" PRIMARY KEY (id )
);

CREATE TABLE bookstore."Invoice"
(
  id bigint NOT NULL,
  date date,
  amount double precision,
  number character varying(200),
  CONSTRAINT "Invoice_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Invoice_number_key" UNIQUE (number )
);

CREATE TABLE bookstore."Notification"
(
  id bigint NOT NULL,
  subject character varying(200),
  message character varying(2000),
  customer_id bigint,
  CONSTRAINT "Notification_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Notification_customer_id_fkey" FOREIGN KEY (customer_id)
      REFERENCES bookstore."Customer" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE bookstore."Order"
(
  customer_id bigint,
  book_id bigint,
  amount integer,
  id bigint NOT NULL,
  date date,
  CONSTRAINT "Order_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Order_book_id_fkey" FOREIGN KEY (book_id)
      REFERENCES bookstore."Book" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Order_customer_id_fkey" FOREIGN KEY (customer_id)
      REFERENCES bookstore."Customer" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE bookstore."Shipment"
(
  id bigint NOT NULL,
  date date,
  express boolean,
  customer_id bigint,
  invoice_id bigint,
  shipment_number character varying(200),
  CONSTRAINT "Shipment_pkey" PRIMARY KEY (id ),
  CONSTRAINT "Shipment_customer_id_fkey" FOREIGN KEY (customer_id)
      REFERENCES bookstore."Customer" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Shipment_invoice_id_fkey" FOREIGN KEY (invoice_id)
      REFERENCES bookstore."Invoice" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Shipment_shipment_number_key" UNIQUE (shipment_number )
);

CREATE TABLE bookstore."Shipment_Order"
(
  shipment_id bigint NOT NULL,
  order_id bigint NOT NULL,
  CONSTRAINT shipment_order_pkey PRIMARY KEY (shipment_id , order_id ),
  CONSTRAINT shipment_order_order_id_fkey FOREIGN KEY (order_id)
      REFERENCES bookstore."Order" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT shipment_order_shipment_id_fkey FOREIGN KEY (shipment_id)
      REFERENCES bookstore."Shipment" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE bookstore."WaitingList"
(
  book_id bigint NOT NULL,
  customer_id bigint NOT NULL,
  CONSTRAINT "WaitingList_pkey" PRIMARY KEY (book_id , customer_id )
);

commit;