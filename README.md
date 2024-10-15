This project is a budget calculator built using Java and the Spring Boot framework.
It is a REST API managing incomes and expenses, with data stored in a MySQL database.
The project is containerized using Docker, and Docker Compose manages the setup of two containers: one for the Spring Boot API and another for the MySQL database.

Instructions
Prerequisites: Ensure you have Docker and Docker Compose installed on your machine.

How to Run
1) Clone the repository:
git clone https://github.com/your-username/your-repository.git
cd your-repository
2) Run the app: In the project folder where the docker-compose.yml file is located, run:
docker-compose up

This will pull the necessary images for the Spring Boot API and MySQL and start both containers.
The Spring Boot API will be accessible at http://localhost:5000.
The MySQL database will be running on port 3306.

To stop the application, press Ctrl+C in the terminal and then run:
docker-compose down

Usage
Once the containers are running, the API will need some data to function. You can either add data manually using POST requests or automatically with a test data endpoint.

To add test data (3 expenses and 3 incomes), use the following endpoint:
POST http://localhost:5000/addTestData

To manually add incomes or expenses, send a POST request with the following JSON format:

Income:
{
  "category": "Job-payment",
  "amount": 750
}

Expense:
{
  "category": "BILLS",
  "amount": 280
}

Note:
Income categories can be any string.
Expense categories must be one of the following: BILLS, RENT, MARKET (as defined in the Enum class).

Endpoints
Generate Budget: Calculate the budget by subtracting expenses from incomes.
GET http://localhost:5000/generateBudget

Show Budget: Retrieve the current budget.
GET http://localhost:5000/showBudget

Delete All Data: Remove all income and expense entries.
DELETE http://localhost:5000/deleteAll
