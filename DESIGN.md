## High-Level Design Document for the Reward Point System

## Project Name: Reward Point System

Overview: The Reward Point System is designed to manage and calculate reward points for customers based on their purchases. The system computes reward points based on defined rules and allows users to retrieve reward point reports. It also enables querying of historical and accumulated points over time.


## System Components :

### User Interface (UI): 
   Allows customers to interact with the system, view their reward points, and see their transaction history.

### API Layer (Controller):

*Exposes RESTful endpoints for client interaction.
*RewardPointController handles incoming HTTP requests:
   GET /api/rewardPoints/customer: Retrieve reward points for a customer based on the year and   month.
   GET /api/rewardPoints/customer/all: Retrieve all reward points for a specific customer.
   
### Business Logic Layer (Service):

*RewardPointService handles the logic for calculating reward points and fetching reward point reports.
calculateRewardPoints: Calculates points based on the purchase amount.
getRewardPointReport: Retrieves reward points based on customer ID, year, and month.
getAllRewardPointsForCustomer: Retrieves all reward points for a customer.
Data Layer (Repository):

*RewardPointRepository is responsible for interacting with the database to store and retrieve reward points data.


### Key Features:

*Reward Point Calculation:

The system calculates reward points based on predefined rules:
Points for purchases over 100 are calculated at 2 points per dollar.
Points for purchases between 50 and 100 are calculated at 1 point per dollar.
No points are awarded for purchases under 50.

*Reward Point Report:

The system allows for querying of reward points based on customer ID, year, and month.
Historical points data is also available.

*Customer Reward History:

Customers can view their accumulated reward points for all time periods, with the ability to query by month and year.


### Technologies Used:

*Backend:

Java: Programming language for business logic and API development.
Spring Boot: Framework for creating REST APIs.
Spring Data JPA: For interacting with the relational database.
JUnit: For testing the service and controller layers.

*Database:

MySQL: Relational database for storing customer and reward point information.
JPA/Hibernate: ORM framework for database operations.

*API Endpoints:

GET /api/rewardPoints/customer
Fetch reward points for a customer by month and year.
Parameters:
customerId: Long
year: Integer
month: Integer
Response: A list of reward points for the given customer for the specified year and month.

GET /api/rewardPoints/customer/all
Fetch all reward points for a customer.
Parameters:
customerId: Long
Response: A list of all reward points for the given customer.

### High-Level Flow:
The client sends a request to the controller (via the API endpoints).
The controller delegates the request to the appropriate service method.
The service method performs the necessary business logic (like calculating points or fetching reward reports).
The service returns the result to the controller.
The controller sends back an appropriate HTTP response.


### Database Design:
RewardPoint Table:
id (Primary Key)
customerId (Foreign Key)
points
month
year