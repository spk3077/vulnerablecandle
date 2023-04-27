# Brute-Force Username and Password
A type of brute force attack that cracks valid user credentials not belonging to the attacker.

## Exploitation
**Prerequisites:** None
**Location:** _POST /login_

Brute-Force username and password present within SpringBoot code by not blocking access after consecutive failed login attempts in a short period of time (or other alternative controls).  Below is an exploitation example:

    POST /login
    form-data:
        "username": "admin"
        "password":"password10!"
    
Burp Suite, ZAP, or equivalent technologies can be used to automate brute-forcing attempts against the web application using the general format above.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Login successfully"
    }

NOTE: This vulnerability, unlike the rest, uses _form-data_ not _raw_
