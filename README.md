SWIFT Codes API
The SWIFT Codes API is a RESTful service for managing SWIFT/BIC code data. This service enables you to:

Parse SWIFT Code Data: Import data from a provided file, correctly classifying headquarters (codes ending with XXX) and 
branches (codes with matching first 8 characters).
Store Data: Persist SWIFT data in a database designed for fast, low-latency querying.
The project is implemented in Java using Springboot framework. For data migration and database managing is use 
open-source database library liquibase which improve reliability, and reduce toil as part of database CI/CD process.
Project is containerized using Docker, that helps to build, share, run, and verify application anywhere — without 
environment configuration or management.

All endpoints are accessible at http://localhost:8080.



Start app using docker-compose (path has to be set inside main project directory where docker-compose.yml file 
is located):
docker-compose up --build

Start app tests:
docker-compose --profile test up --build




API Endpoints:
1. Retrieve SWIFT Code Details
   Endpoint:
   GET /v1/swift-codes/{swift-code}

    Description:
    Retrieves details of a SWIFT code. For headquarters (codes ending with XXX), the response includes a list of 
    associated branch codes.

2. Retrieve SWIFT Codes by Country
   Endpoint:
   GET /v1/swift-codes/country/{countryISO2code}

    Description:
    Retrieves all SWIFT code entries (both headquarters and branches) for the specified country.

3. Add a New SWIFT Code Entry
   Endpoint:
   POST /v1/swift-codes/
    
    Description:
    Adds a new SWIFT code entry to the database.

4. Delete a SWIFT Code Entry
   Endpoint:
   DELETE /v1/swift-codes/{swift-code}

    Description:
    Deletes a SWIFT code entry if the swiftCode, bankName, and countryISO2 match the record in the database.

Error Handling
The API returns clear and informative error messages for edge cases such as invalid inputs, resource not found, or 
internal server errors.

Testing
To ensure the reliability of the API, the project includes thorough unit and integration tests. These tests cover:

* Unit Tests:
  Testing individual components and services to ensure that each piece works correctly in isolation.

* Integration Tests:
  Verifying the interactions between components, including API endpoints, database operations, and external libraries.

Contact
For questions, feedback, or additional information, please contact:
Email: fi.dziala@gmail.com