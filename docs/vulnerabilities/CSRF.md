# Cross-site Request Forgery (CSRF)
The user visits an attacker controlled/compromised site, and the site triggers the user's browser to perform actions against an authenticated and/or trusted site without the user's interaction.

## Exploitation
**Location** _Entire Application_
CSRF is present since the application doesn't have cryptographically random values that are newly generated for each critical request (PUT and POST).  Below is an exploitation example exploiting changing passwords with the attacker changing the password of _admin_ to _password10!_:

    # HTML
    <html>
        <body>
  
        </body>
    </html>


