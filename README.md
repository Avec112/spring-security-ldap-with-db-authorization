# Login Demo 

## Relevant technology 
* Java 14
* Spring Boot v2.2.7
* Spring Security LDAP
* unboundid-ldapsdk

## LDAP config
See the file [test-ldap.lidf](https://github.com/Avec112/spring-security-ldap/blob/master/src/main/resources/test-ldap.ldif)

## How to
* Start server
  * Use IDE or,
  * mvn spring-boot:run
* Access http://localhost:8080
* Click "here"
* Type ben/benspassword
* You should now have been authenticated and redirected to http://localhost:8080/hello


