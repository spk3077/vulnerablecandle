use csec;
INSERT INTO ecom_order (id, purchase_date, user_id) VALUES (1, '2020-06-22', (SELECT id from ecom_user WHERE id=7));
INSERT INTO ecom_order_item (id, quantity, order_id, product_id) VALUES (1, 10, (SELECT id from ecom_order WHERE id=1), (SELECT id from ecom_product WHERE id=1));
INSERT INTO ecom_order_item (id, quantity, order_id, product_id) VALUES (2, 3, (SELECT id from ecom_order WHERE id=1), (SELECT id from ecom_product WHERE id=3));
