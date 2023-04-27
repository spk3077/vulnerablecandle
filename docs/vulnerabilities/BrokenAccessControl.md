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
