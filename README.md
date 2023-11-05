# digibooklet-springboot-server
Springboot Application that acts as backend for digibooklet (momobooklet) android app. 
It is hosted on heroku , Uses JWT for Security and Thymeleaf for  template Engine. 
## ENDPOINTS 
The applications exposes the following enpoints support the following features

### TRANSACTION_DATA RELATED
- upload transaction data to server
- download transaction data from server
### BACKUP RELATED
implement a full data backup of user's data  stored as zip files in server 
### USER RELATED 
- register user accounts 
- authenticate users 
## TECH-STACK 
### DATABASE 
- [Postgres] (https://www.postgresql.org/)
- [Spring Data JPA] (https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)
### DEPENDENCY MANAGEMENT 
- [Gradle] (https://plugins.gradle.org/plugin/io.spring.dependency-management)
### TEMPLATE ENGINE 
- [Thymelead] (https://www.thymeleaf.org/documentation.html)
### SECURITY 
- [ JSON Web Token] (https://jwt.io/)
- [Spring Security] (https://spring.io/projects/spring-security/)
