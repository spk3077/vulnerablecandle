# Unenforced Password Complexity Requirements
When creating accounts normally users are forced to meet complexity requirements to help users from having their accounts compromised due to weak passwords (brute-force, rainbow table, dictionary attacks, etc.).  This vulnerability is when the website states the complexity requirements 

## Exploitation
**Prerequisites:** _None_

**Location:** _POST /users_

The Angular application states on the Signup page that the password has complexity requirements, that being requires one number and one symbol.  We can see from the exploitation below that these requirements are not enforced either by the Angular application nor SpringBoot:

    POST /users
    raw:
    {
        "username" : "unenforced",
        "password" : "password"
    }


Successful exploitation looks like:

    {
	    "success": true,
	    "code": 200,
	    "message": "Success"
    }

## Burp Suite Example Exploitation

    POST /users HTTP/1.1
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
    Connection: close

    {"username":"unenforced","password":"password"}

