
## Arithmetic Calculator - Back End

The following project was build with the following tech stack:
- Java v21
- Spring Boot v3.3.2 
- Hibernate
- H2 in-memory DB
- Lombok
- JUnit
- Mockito
- OpenAPI + Swagger

### Quick start

- Clone the repo: `git clone https://github.com/dancarreon/arithmetic-calculator.git`
- Verify you are using Java SDK v21 and Maven v3.9.8
- The project includes Maven plugins that helps you clean, install and the project, if your IDE supports Maven you can run all of them through the UI, otherwise, verify you have Maven Environment Variables (M2_HOME and Path) so you can run the `mvn clean install` command 
- Start the app: `mvnw spring-boot:run`
- Open browser: http://localhost:8080 or go directly to Swagger docs: http://localhost:8080/v1/swagger-ui/index.html

### Notes:
- Several default Users have been created in order to test this app, you can use any of them to log in and perform any operation:
  - Username: `user1@test.com`
  - Password: `password`
- The rest of the users follow the same pattern, just modify the number in the `username` for the next consecutive integer
- Once a User has run out of budget it won't be possible to keep doing operations, you will have to choose another User, manually reset their budget or reset the app completely (to wipe out the in-memory DB)

