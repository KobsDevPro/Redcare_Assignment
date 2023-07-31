# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
###
Steps for execution -
1. Download this project and open in intellij or eclipse.
2. Then run - mvn clean install.
3. Right click on AssignmentApplication.java and Select "Run HomeworkApplication.java"
4. Application will start. Now user can navigate to postman or GoogleChrome browser to run the APIs
##
API URLS -

http://localhost:8080/sortByStar

http://localhost:8080/sortByTopNumber?number=<topNumber> [expected value is any integer like -30,50,100]

http://localhost:8080/filterByDate?date=<date> [desired date in this format YYYY-MM-DD, 2023-07-20]

http://localhost:8080/filterByLanguage?language=<language> [language like- html,go,python etc]
