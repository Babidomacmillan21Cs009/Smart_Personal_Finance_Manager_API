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
| Endpoint                                                        | Method | Description                                 |
|-----------------------------------------------------------------|--------|---------------------------------------------|
| `/api/register`                                                 | POST   | Register a new user                         |
| `/api/login`                                                    | POST   | Login and get JWT token                     |
| `/api/transactions/`                                            | GET    | Get all transactions                        |
| `/api/transactions/user/{userId}`                               | POST   | Add a new transaction                       |
| `/api/transactions//user/update-user/{userId}`                  | PUT    | Update a transaction                        |
| `/api/transactions//user/{userId}/delete-Transaction/{transId}` | DELETE | Delete a transaction                        |
| `/api/analytics//monthly/{month}/{year}`                        | GET    | View monthly summary of income and expenses |


