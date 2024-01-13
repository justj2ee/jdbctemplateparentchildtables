drop table customer if exists;
create table customer
(id long not null,
 fname varchar(50),
 lname varchar(50),
 address varchar(100) not null, 
 city varchar(100) not null,
 state varchar(4) not null,
 zip_cd varchar(10) not null,
 phone_no varchar(15),
 primary key (id));
 
drop table product if exists;
create table PRODUCT
(id  long not null,
 description varchar(100),
 price number(20,2),
 primary key (id));
 
 drop table orders if exists;
 create table orders
 (id long not null,
  cust_id long not null,
  order_date date not null,
  primary key(id),
  CONSTRAINT fk_customer FOREIGN KEY(cust_ID) REFERENCES customer(ID) ON DELETE CASCADE);
 
 drop table orders_items if exists;
  CREATE table ORDER_ITEMS
 (id long not null,
  order_id long not null, 
  prod_id long not null,
  primary key (id,order_id,prod_id),
  CONSTRAINT fk_order FOREIGN KEY(order_ID) REFERENCES orders(ID) ON DELETE CASCADE,
  CONSTRAINT fk_product FOREIGN KEY(PROD_ID) REFERENCES PRODUCT(ID) ON DELETE CASCADE);
