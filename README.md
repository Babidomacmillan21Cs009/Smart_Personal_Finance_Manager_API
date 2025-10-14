# Smart Personal Finance Manager API

## What It Does
This project is a backend API to help users manage their personal finances. Users can track income, expenses, and savings easily.

## Main Features
- User registration and login with secure authentication
- Add, update, and delete income and expense transactions
- Categorize transactions for better tracking
- View monthly and category-wise financial summaries

## Tech Used
- **Java 17**  
- **Spring Boot**  
- **MySQL Database**  
- **Spring Security with JWT**  
- **Maven** for build management

## Skills Learned / Used
- Designing and building **RESTful APIs**
- Database modeling and querying
- Implementing secure **authentication and authorization**
- Writing **clean and modular backend code**

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/smart-personal-finance-manager-api.git
2. Configure MySQL database in `application.properties`
3. Run the project:
   ```bash
   mvn spring-boot:run

## API / How to Use
| Endpoint                 | Method | Description                                 |
|--------------------------|--------|---------------------------------------------|
| `/api/users/register`    | POST   | Register a new user                          |
| `/api/users/login`       | POST   | Login and get JWT token                      |
| `/api/transactions`      | GET    | Get all transactions for a user             |
| `/api/transactions`      | POST   | Add a new transaction                        |
| `/api/transactions/{id}` | PUT    | Update a transaction                         |
| `/api/transactions/{id}` | DELETE | Delete a transaction                         |
| `/api/summary/monthly`   | GET    | View monthly summary of income and expenses |


