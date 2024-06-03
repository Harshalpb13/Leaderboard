# Leaderboard

This is a RESTful API service developed using Spring Boot to manage the leaderboard for a coding platform. The application is designed to handle CRUD operations for registering users, updating their scores, and retrieving leaderboard information. MongoDB is used as the database to persist user data.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Spring Boot
- MongoDB
- Gradle (Dependency Management)
- Postman (API Testing)

## Project Structure

The project follows a typical Spring Boot application structure, with the following key packages:

- `controller`: Contains the REST controller classes responsible for handling HTTP requests and responses.
- `entity`: Contains the entity classes representing domain objects (e.g., User).
- `service`: Contains the service classes responsible for business logic and interaction with the repository layer.
- `repository`: Contains the repository classes responsible for database operations.

## Running the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/codehack-leaderboard.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd codehack
   ```

3. **Build the Project**:
   ```bash
   ./gradlew build
   ```

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

5. **Accessing the Application**:
   The application will be accessible at `http://localhost:8083/coderhack`.

## Endpoints

The application provides the following endpoints:

- `GET /users`: Retrieve a list of all registered users sorted by score.
- `GET /users/{userId}`: Retrieve details of a specific user by their ID.
- `POST /users`: Register a new user to the contest.
- `PUT /users/{userId}`: Update the score of a specific user.
- `DELETE /users/{userId}`: Deregister a specific user from the contest.

## Testing

You can test the application using tools like Postman or by writing JUnit test cases. Postman collections are available in the repository for easier testing.
