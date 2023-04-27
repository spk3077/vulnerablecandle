#!/bin/bash

SCRIPTS_DIR=./temp
#read -sp "Enter DB Password : " pswd
pswd="csec77499981"
# for file in $SCRIPTS_DIR/*.sql
#     do mysql -u root --password=$pswd < $file
# done

mysql -h 127.0.0.1 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomProduct.sql
mysql -h 127.0.0.1 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomUserInfo.sql
mysql -h 127.0.0.1 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomTags.sql
mysql -h 127.0.0.1 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomProductReview.sql
mysql -h 127.0.0.1 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomOrders.sql
