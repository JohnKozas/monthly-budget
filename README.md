This is a budget calculator made with Java using the spring boot framework.
It is a REST API managing incomes and expenses storing the data in a MySQL database.
I have containerised it in a docker image with two containers, one for the database and one for the Java app.

Instructions
This project uses Docker Compose to run both the Spring Boot API and the MySQL database so in order to run Docker and Docker Compose should be installed on your machine.

How to Run
1) Clone the repository:
git clone https://github.com/your-username/your-repository.git
cd your-repository
2) Run the app: In the project folder where the docker-compose.yml file is located, run:
docker-compose up

This will automatically pull the necessary images (Spring Boot app and MySQL) and start the containers.
The Spring Boot API will be accessible at http://localhost:5000.
The MySQL database will be running on port 3306.
To stop the application, press Ctrl+C in the terminal and then run:
docker-compose down

After you have instatiated the docker image the API will need some data to work, so you can add them with individual POST commands or you can add some data with the command "/addTestData" in which 3 expenses and 3 incomes are added.
The port for the app is 5000, so "http://localhost:5000" is the url before any request.
To add incomes and expenses the body must be in JSON of either income or expense with this format:
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
The Income category is in String format and can be anything the user types, however the expense category is Enum class and can be: BILLS, RENT, MARKET.
After at least one expense and one income is added you can calculate the budget by typing a GET "/generateBudget" to calculate the budget by subtracting the expenses from the incomes.
With "/showBudget" you can retrieve the existing budget.
If you want all the entities to be deleted make a DELETE request with the url "/deleteAll".
