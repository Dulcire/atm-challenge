# Java Challenge

# Description
Application to simulate an ATM using Springboot

### Pre - requisites ###

* Git
* Install JDK 1.8
* Define JDK in JAVA_HOME
* Install Apache Maven 3
* Define bin directory PATH
* Mysql Database 5.+
  * user:root
  * password:root
  * You have to create a shchema named: ***atm-challenge***
     

***You can change DB this properties in properties.yml***
### Build & Usage ###

* Clone atm-challenge to in/your/path: 
*     git clone https://github.com/Dulcire/atm-challenge.git
* Go to your atm-challenge: 
*     cd atm-challenge
* execute: 
*     mvn clean install
* execute:
*     java -jar ./target/atm-challenge-1.0.jar

#### Liquidbase ####
The application use Liquidbase to fill tables in atm-challenge database.


####Swagger route ####
* If you want to test the application you can go to the following path in your browser: 
*     http://localhost:8080/swagger-ui.html
* if you start the application in other route should user this: 
*     http://{{yourserver}}:{{port}}/swagger-ui.html
### Docker ### 
* To create the image run Dockerfile
  *      docker build -t atm-challenge-docker .
* You should have a Mysql database in the same network