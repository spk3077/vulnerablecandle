# vulnerablecandle
Mom and Pop's Candle Shop" or vulnerablecandle is a realistic, modern, vulnerable e-Commerce web application that allows customers to browse and purchase homemade candles. The e-Commerce contains the expected essentials: functional user accounts, filters to apply on products, product reviews, an individualized shopping cart, and a visible transaction history. Supporting functionality is also present such as changing passwords, editing the user profile picture, The application is built using a modern technology stack including Spring Boot for the backend API, Angular framework for the frontend, and MySQL for the database.

![Alt text](docs/images/homePage.png?raw=true "Title")

## How does it Work?
![Alt text](docs/images/appDesign.png?raw=true "Title")

## Documentation
Currently we only have our exploitation documentation to assist students or professors in exploiting the vulnerable web application.  These files containing instructions on how to exploit each vulnerability can be found [here](https://github.com/spk3077/vulnerablecandle/tree/subset4/docs/vulnerabilities).

## Install
Instructions provided are oriented to Debian distributions such as Ubuntu.  Other operating systems and Linux distributions can be used to run, but may require additional or different utilities and commands.

    # Clone our repository
    git clone https://github.com/spk3077/vulnerablecandle
    
    # Install required packages
    sudo apt install docker docker-compose 

## Running

    # Enter cloned directory
    cd vulnerablecandle

    # Run Docker-Compose and wait!
    sudo docker-compose up

## Contact Us
Have any questions or advice?  Email us at our university email addresses:
* Sean Kells (Angular Dev) [spk3077@g.rit.edu](mailto:spk3077@g.rit.edu)
* Hancheng Cao (SpringBoot Dev) [hc6674@g.rit.edu](mailto:hc6674@g.rit.edu)
* Cameron MacDonald (Assets & Docker) [hc6674@g.rit.edu](mailto:hc6674@g.rit.edu)
