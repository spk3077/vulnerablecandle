#!/bin/bash

SCRIPTS_DIR=/temp
pswd="csec77499981"

#waiting for mysql to boot
sleep 1m
sleep 30s

mysql -h 172.28.1.2 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomProduct.sql
mysql -h 172.28.1.2 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomUserInfo.sql
mysql -h 172.28.1.2 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomTags.sql
mysql -h 172.28.1.2 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomProductReview.sql
mysql -h 172.28.1.2 -u root --password=$pswd < $SCRIPTS_DIR/insertEcomOrders.sql
