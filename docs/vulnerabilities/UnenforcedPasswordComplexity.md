# Unenforced Password Complexity Requirements
When creating accounts normally users are forced to meet complexity requirements to help users from having their accounts compromised due to weak passwords (brute-force, rainbow table, dictionary attacks, etc.).  This vulnerability is when the website states the complexity requirements 

## Exploitation
**Prerequisites:** _None_

**Location:** _POST /users_

The Angular application states on the Signup page that the password has complexity requirements, that being requires one number and one symbol.  We can see from the exploitation below that these requirements are not enforced either by the Angular application nor SpringBoot:

    POST /users
    raw:
    {
        "username" : "admin",
        "password" : "password"
    }


Successful exploitation looks like:

    {
	    "success": true,
	    "code": 200,
	    "message": "Success"
    }
