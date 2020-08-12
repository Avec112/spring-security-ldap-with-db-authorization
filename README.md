# Login Demo 
Demonstration of web application login using LDAP and Spring Boot. 

## Relevant technology 
* Java 14
* Spring Boot v2.2.7
* Spring Security LDAP
* unboundid-ldapsdk

## LDAP config
See the file [test-ldap.lidf][1]

[1]: https://github.com/Avec112/spring-security-ldap/blob/master/src/main/resources/test-ldap.ldif

## How to
* Start server
  * Use IDE or,
  * mvn spring-boot:run
* Access http://localhost:8080
* Click the link "here" or http://localhost:8080/hello
* Type ben/benspassword
* You should now have been authenticated and redirected to http://localhost:8080/hello


