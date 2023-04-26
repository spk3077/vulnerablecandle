# Price Manipulation
Some e-commerce applications mistakenly accept the price of a product as a user input rather than use the database stored value.  vulnerablecandle possesses this vulnerability.

## Exploitation
**Location:** _POST /shoppingcarts/add/PRODUCTID/QUANTITY_

Price manipulation involves the Angular application sending an _itemPrice_ POST parameter that specifies the price used to purchase the candle at.  While this price is usually set as the database value, the attacker can manipulate it.  SpringBoot then uses this manipulated price throughout the checkout process.  Below is an exploitation example:

    POST /shoppingcarts/add/10/2
    raw:
    {
        "itemPrice" : 1.00
    }

Here we added two of the product with ID 10 (Cedar Candle) and stated the price of one Cedar candle as $1.00.  When the attacker later checks out their shoppingcart they will pay $2.00 for both candles.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Success"
    }
