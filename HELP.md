
### Steps for execution -
1. Download this project and open in intellij or eclipse.
2. Then run - mvn clean install.
3. Right click on AssignmentApplication.java and Select "Run AssignmentApplication.java"
4. Application will start. Now user can navigate to postman or GoogleChrome browser to run the APIs
##
API URLS -

http://localhost:8080/sortByStar

http://localhost:8080/sortByTopNumber?number=<topNumber> [expected value is any integer like -30,50,100]

http://localhost:8080/filterByDate?date=<date> [desired date in this format YYYY-MM-DD, 2023-07-20]

http://localhost:8080/filterByLanguage?language=<language> [language like- html,go,python etc]
