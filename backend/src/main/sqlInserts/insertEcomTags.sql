use csec;
INSERT INTO ecom_tag (id, name) VALUES (1, 'Cute');
INSERT INTO ecom_tag (id, name) VALUES (2, 'Popular');
INSERT INTO ecom_tag (id, name) VALUES (3, 'On Sale');
INSERT INTO ecom_tag (id, name) VALUES (4, 'Trending');
INSERT INTO ecom_tag (id, name) VALUES (5, 'For Car');
INSERT INTO ecom_tag (id, name) VALUES (6, 'Unique');
INSERT INTO ecom_tag (id, name) VALUES (7, 'White');
INSERT INTO ecom_tag (id, name) VALUES (8, 'Red');
INSERT INTO ecom_tag (id, name) VALUES (9, 'Orange');
INSERT INTO ecom_tag (id, name) VALUES (10, 'Yellow');
INSERT INTO ecom_tag (id, name) VALUES (11, 'Green');
INSERT INTO ecom_tag (id, name) VALUES (12, 'Blue');
INSERT INTO ecom_tag (id, name) VALUES (13, 'Purple');
INSERT INTO ecom_tag (id, name) VALUES (14, 'Brown');
INSERT INTO ecom_tag (id, name) VALUES (15, 'Pink');
INSERT INTO ecom_tag (id, name) VALUES (16, 'Black');

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=1));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=1));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=2));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=15), (SELECT id from ecom_product WHERE id=2));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=3));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=3));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=3));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=4));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=4));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=4));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=4));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=12), (SELECT id from ecom_product WHERE id=4));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=5));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=5));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=9), (SELECT id from ecom_product WHERE id=5));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=6));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=6));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=6));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=6));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=7));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=10), (SELECT id from ecom_product WHERE id=7));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=8));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=8));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=8));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=8));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=9));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=9));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=9));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=10));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=10));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=10));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=11));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=11));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=11));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=12));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=12));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=12));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=13));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=13));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=13));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=8), (SELECT id from ecom_product WHERE id=13));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=14));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=14));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=14));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=9), (SELECT id from ecom_product WHERE id=14));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=15));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=15));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=15));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=16));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=16));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=8), (SELECT id from ecom_product WHERE id=16));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=17));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=17));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=17));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=18));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=18));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=18));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=13), (SELECT id from ecom_product WHERE id=18));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=19));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=19));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=19));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=12), (SELECT id from ecom_product WHERE id=19));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=20));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=20));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=20));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=21));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=21));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=21));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=21));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=15), (SELECT id from ecom_product WHERE id=21));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=22));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=23));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=23));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=23));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=23));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=23));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=24));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=24));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=24));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=25));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=25));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=25));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=25));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=25));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=26));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=26));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=26));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=27));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=27));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=27));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=27));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=27));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=28));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=28));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=14), (SELECT id from ecom_product WHERE id=28));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=29));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=29));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=29));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=29));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=30));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=30));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=30));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=30));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=30));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=31));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=31));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=16), (SELECT id from ecom_product WHERE id=31));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=32));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=32));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=32));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=33));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=33));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=33));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=33));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=33));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=16), (SELECT id from ecom_product WHERE id=33));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=34));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=34));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=34));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=34));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=9), (SELECT id from ecom_product WHERE id=34));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=35));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=35));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=35));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=35));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=10), (SELECT id from ecom_product WHERE id=35));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=36));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=36));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=36));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=36));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=36));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=16), (SELECT id from ecom_product WHERE id=36));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=37));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=37));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=37));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=37));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=38));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=38));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=16), (SELECT id from ecom_product WHERE id=38));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=39));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=39));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=39));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=39));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=39));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=39));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=40));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=40));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=8), (SELECT id from ecom_product WHERE id=40));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=41));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=41));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=5), (SELECT id from ecom_product WHERE id=41));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=41));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=16), (SELECT id from ecom_product WHERE id=41));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=42));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=3), (SELECT id from ecom_product WHERE id=42));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=42));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=7), (SELECT id from ecom_product WHERE id=42));

INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=1), (SELECT id from ecom_product WHERE id=43));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=2), (SELECT id from ecom_product WHERE id=43));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=4), (SELECT id from ecom_product WHERE id=43));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=6), (SELECT id from ecom_product WHERE id=43));
INSERT INTO product_tag (tag_id, product_id) VALUES ((SELECT id from ecom_tag WHERE id=11), (SELECT id from ecom_product WHERE id=43));
