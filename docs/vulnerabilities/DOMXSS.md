# Document Object Model Cross Site Scripting (DOM XSS)
Document Object Model (or DOM) XSS variants attack victims by abusing client-side JavaScript processing untrusted input without input validation and writing this unsafe input back to the DOM.

## Exploitation
**Location** _ShopPage Search Bar_

This vulnerability is solely within the client-side Angular Application.  The input entered into the search bar is placed directly into the DOM with DOM sanitization bypassed intentionally and rewritten to escape Angular removing _script_ tags.  Below is picture of exploitation:

![Alt text](../images/domxss.png?raw=true "Title")


