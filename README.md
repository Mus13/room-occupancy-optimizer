# room-occupancy-optimizer
Crafting a Spring Boot RESTful API using Java 17 to optimize room assignment based on willingness to pay. Tracking progress with clear commits, employing TDD for clean and efficient code. Aiming to maximize customer satisfaction and revenue.

# Problem Overview:

Hotel establishments face the nightly task of allocating available rooms to potential guests. The scenario involves a fixed number of free rooms and a pool of customers eager to book accommodations. The hotels offer two room categories: Premium and Economy.

# Key Rules:

1. Premium Room Exclusivity:

Customers willing to pay EUR 100 or more are exclusively assigned to Premium rooms.

2. Optimizing Premium Room Occupancy:

Premium rooms accommodate lower-paying customers only if Economy rooms are fully occupied with customers willing to pay less.

3. Upgrade Preference for High-Paying Customers:

The highest paying customers below EUR 100 receive preference for upgrades.
Fixed Customer Budget:

4. Customers have a specific budget in mind, representing the amount they are willing to pay for the night.

# Expected Output:

The API should return a JSON object with the following details:

- usagePremium (Integer): Number of Premium rooms occupied.
- revenuePremium (Double): Total revenue generated from Premium rooms.
- usageEconomy (Integer): Number of Economy rooms occupied.
- revenueEconomy (Double): Total revenue generated from Economy rooms.

# Installation

1. Clone the Repository:

git clone https://github.com/your-username/room-occupancy-manager.git

2. Navigate to the Project Directory:

cd room-occupancy-manager

3. Build the Project:

./mvnw clean install

# Usage
1. Run the Application:

./mvnw spring-boot:run

2. Access the API:

- API Endpoints:
GET /api/room-distribution:
  http://localhost:8080/api/room-distribution?availablePremiumRooms={availablePremiumRooms}&availableEconomyRooms={availableEconomyRooms}
- Parameters:

availablePremiumRooms (Integer)

availableEconomyRooms (Integer)

- Example: 

- http://localhost:8080/api/room-distribution?availablePremiumRooms=3&availableEconomyRooms=3

# Tests
- To run tests, use the following command:

./mvnw test