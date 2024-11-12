# Medical API

APImed is a RESTful API developed with Spring Boot designed to manage medical data. The API allows registering doctors, querying their information, and includes a JWT-based authentication and authorization system to secure the endpoints.

Project Overview

This API manages medical records, including functionalities for adding, updating, listing, and deleting doctor data. It uses JWT (JSON Web Token) for security, ensuring that most endpoints are protected and only accessible to authenticated users.



### Security Configuration

The security configuration uses Spring Security with JWT to protect most endpoints, allowing only authenticated users to access them. However, the endpoints for user registration (/register) and login (/login) are publicly accessible.

After a user logs in successfully, the system returns a JWT token, which should be sent in the Authorization header for all subsequent requests to secured endpoints.

## Available Endpoints

Below is a list of the main endpoints available in APImed, including the request method, security requirements, and the request structure for each one.

1. /register 

Method: POST

Protection: Public (no authentication required)

Description: Allows a new user to register in the system.



2. /login 

Method: POST

Protection: Public (no authentication required)

Description: Generates a JWT token for a user who successfully logs in.

Expected Response: Returns a JWT token, which should be used in the Authorization header as Bearer <token_jwt> to access secured endpoints.



3. /doctors

Method: GET

Protection: Protected (authentication required)

Description: Retrieves a list of only active doctors.

Request Header:

Authorization: Bearer <token_jwt>



4. /all_doctors

Method: GET

Protection: Protected (authentication required)

Description: Retrieves a list of all register doctors.

Request Header:

Authorization: Bearer <token_jwt>



5. /doctor/{id}

Method: GET

Protection: Protected (authentication required)

Description: Retrieves the details of a specific doctor based on their ID.

Path Parameter:

id: ID of the doctor.

Request Header:

Authorization: Bearer <token_jwt>



6. /post_doctor

Method: POST

Protection: Protected (authentication required)

Description: Allows registering a new doctor in the database.

Request Header:

Authorization: Bearer <token_jwt>



7. /doctor/{id}

Method: PUT

Protection: Protected (authentication required)

Description: Allows updating the information of a specific doctor.

Path Parameter:

id: ID of the doctor to update.

Request Header:

Authorization: Bearer <token_jwt>

8. /doctor/{id}

Method: DELETE

Protection: Protected (authentication required)

Description: Allows you to deactivate a doctor, not delete him from the database.

Path Parameter:

id: ID of the doctor to deactivate.

Request Header:

Authorization: Bearer <token_jwt>


## Example to use

To use the API, you must first create the database in MySQL, then set the database url and MySQL user credentials in the application.properties file.

spring.datasource.url=jdbc:mysql://localhost/"your_databases_name"

spring.datasource.username=your_username

spring.datasource.password=your_password

Once you've done that, follow these steps:

1. Register a User:

Endpoint: /register

Method: POST

Request Body:

    {
    
        "login": "username",
      
        "password": "userpassword"
    
    }

2. Log in to Obtain a Token:

Endpoint: /login

Method: POST

Request Body:

      {
      
          "login": "user",
        
          "password": "user123"
      
      }

Copy the token from the response to use in the Authorization header for secured endpoints.

3. Access Secured Endpoints:

Endpoint: /post_doctor

Method: POST

Request Header:

Authorization: Bearer <token_jwt>

Example of request Body:

    {
      
        "name": "Dr. John Doe",
      
        "email": "johndoe@example.com",
      
        "phone": "123456789",
        
        "document":"12345"
        
        "specialty": "CARDIOLOGY",
      
        "address": {
      
            "street": "Main St",
        
            "district": "North",
         
            "city": "Example City",
        
            "number": "101",
        
            "complement": "Apt 5"
      
        }
    
    }

For the specialty field, only can be this four options: ORTHOPEDIC, CARDIOLOGY, GYNECOLOGY or PEDIATRICS.
Document field need minimum 4 digits and maximum 6 digits.

Endpoint: /doctor

Method: POST

Request Header:

Authorization: Bearer <token_jwt>

Example of request Body:

    {
      
        "name": "Dr. John Doe",
      
        "email": "johndoe@edited_example.com",
      
        "phone": "123456789",
        
        "document":"12345"
        
        "specialty": "CARDIOLOGY",
      
        "address": {
      
            "street": "Main St",
        
            "district": "North",
         
            "city": "edited example City",
        
            "number": "101",
        
            "complement": "Apt 5"
      
        }
    
    }

