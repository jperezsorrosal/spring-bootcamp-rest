SELECT 'CREATE DATABASE web_customer_tracker ENCODING "UTF8"'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'web_customer_tracker');

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id serial PRIMARY KEY,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL
);

ALTER SEQUENCE customer_id_seq RESTART WITH 6 INCREMENT BY 1;

--select nextval('customer_id_seq');
select currval('customer_id_seq');

--
-- Dumping data for table `customer`
--


INSERT INTO customer VALUES
	(1,'David','Adams','david@luv2code.com'),
	(2,'John','Doe','john@luv2code.com'),
	(3,'Ajay','Rao','ajay@luv2code.com'),
	(4,'Mary','Public','mary@luv2code.com'),
	(5,'Maxwell','Dixon','max@luv2code.com');
