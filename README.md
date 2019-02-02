# contactBookApp
Contact Book App CRUD repo using Spring Boot

Technologies - 
Spring boot,
In memory database - H2


Server will start on port - 8000

To access APIs - 

GET all contacts - localhost:8000/api/


POST contact - localhost:8000/api/contact


PUT contact - localhost:8000/api/contact/{id}


DELETE contact - localhost:8000/api/contact/{id}


Request body for APIs-

{"name":"name","email":"email","phone":"phone"}


How to run - 

java -jar  target/contactbook-2.0.0.RELEASE.jar
