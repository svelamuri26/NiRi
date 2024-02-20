
select * from  cart_item where cart_id=1

select * from  orders
select * from  cart_item

select ci1_0.id,ci1_0.cart_id,ci1_0.order_id,ci1_0.product_id,ci1_0.quantity,ci1_0.status,ci1_0.user_id from cart_item ci1_0 where ci1_0.cart_id=100
insert into orders (order_date,total_price,user_id) values (NOW(6),1112,1)

select p1_0.id,p1_0.description,p1_0.name,p1_0.price,p1_0.stock from products p1_0

drop table orders
drop table cart_item
describe cart_item
describe orders
describe products
describe user
show tables
ALTER TABLE cart_item MODIFY order_id int(11);
ALTER TABLE cart_item ADD COLUMN  cart_id int(11);
ALTER TABLE orders MODIFY id int(11);
ALTER TABLE cart_item DROP FOREIGN KEY FK3mu9lcrqocn2rdcm6xhbqrg3b;
ALTER TABLE CART_ITEM DROP FOREIGNKEY order_id ;

CREATE TABLE cart_item (
	id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    order_id INT,
    quantity INT,
    product_id BIGINT,
    user_id BIGINT,
    cart_id INT,
    status varchar(255)

);
CREATE TABLE cart_item (
	id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    order_id INT,
    quantity INT,
    product_id BIGINT,
    user_id BIGINT,
    cart_id INT,
    status varchar(255)
);

CREATE TABLE orders (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	order_id INT,
    user_id BIGINT,
    order_date datetime(6),
    total_price double
);