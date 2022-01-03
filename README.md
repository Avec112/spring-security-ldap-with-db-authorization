# Login Demo 
Demonstration of web application login using Spring Security LDAP, Spring Data JPA and Spring Boot. 
This demo combines LDAP authentication and fetches authorities (roles for authorization) from database with help of JPA. 
Authentication is implemented, but Authorization is not. 

## Dependencies 
* Java 11
* Spring Boot v2.4.0
* Spring Data JPA  
* Spring Security LDAP
* unboundid-ldapsdk
* and more

## LDAP config
See the file [test-ldap.lidf][1]

[1]: https://github.com/Avec112/spring-security-ldap-with-db-authorization/blob/master/src/main/resources/test-ldap.ldif

## How to
* Start server
  * Use IDE or,
  * mvn spring-boot:run
* Access http://localhost:8080
* Click the link "here" or http://localhost:8080/hello
* Authentication:
  * ben/ben => Roles: USER and ADMIN
  * andy/andy => Roles: USER
* You should now have been authenticated and redirected to http://localhost:8080/hello


