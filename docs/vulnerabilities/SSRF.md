# Server-Side Request Forgery (SSRF)
Server-side Request Forgery (SSRF) attack triggers the web application to perform requests for you.  This could be against the web application itself, website internally located resources, external systems on the infrastructure and more!

## Exploitation
**Prerequisites:** _None_

**Location:** _GET /products/stock/?url=...&id=..._

While the Angular application by default fetches the product stock by using the url parameter to specify _http://localhost:8081/products/stock/?id=ID_, a redirect to the same endpoint acting as a substitute for an external service, it can be manipulated to fetch alternative resources.  Below are two exploitation examples, one fetching an external webpage and the other fetching an internal resource:

    # Fetching external resource, google homepage
    GET /products/stock?url=http://google.com

    # Fetching internal resource, product information
    GET /products/stock?url=http://localhost:8081/products/1


Successful exploitation looks like:

    null ...