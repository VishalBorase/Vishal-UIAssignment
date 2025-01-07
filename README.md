# Project Name : Reward Point System

## Project Description : The **Reward Point System** allows customers to earn and track reward points based on their purchases. The system calculates reward points and stores them in a relational database, enabling customers to view their rewards at any time.

## Features:
- **Reward Points Calculation**: Points are awarded based on predefined rules.
- **Monthly and Annual Reports**: Customers can view their reward points for specific months or for all time.
- **Customer History**: Retrieve a full history of reward points for a customer.

## Key API Endpoints

1. **GET /api/rewardPoints/customer**: Retrieve the reward points for a customer for a specific month and year.
2. **GET /api/rewardPoints/customer/all**: Retrieve all reward points for a customer.

## Technologies Used :
- **Backend**: Java, Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Testing**: JUnit, MockMvc

## High-Level Design
- **API Layer**: The `RewardPointController` exposes endpoints for fetching reward points.
- **Business Logic Layer**: The `RewardPointService` calculates and fetches reward points.
- **Data Layer**: The `RewardPointRepository` handles database interaction.