# Rate Management API

A Spring Boot microservice that manages product rates with dynamic currency support and price formatting. This service provides a comprehensive REST API for handling price rates across different products and brands.

## Features

### Core Functionality
- Create and manage product rates with validation
- Retrieve rates by ID with formatted prices and currency symbols
- Update rate prices with automatic currency formatting
- Delete rates with proper validation
- Advanced rate search by date, product, and brand
- Real-time currency integration for accurate price formatting

### Technical Features
- Automatic decimal formatting based on currency specifications
- Period overlap detection for rate validity
- Cross-origin support for frontend integration
- Comprehensive error handling
- Input validation and sanitization
- RESTful API design

## Technical Stack

### Core Technologies
- Java 17
- Spring Boot 3.2.2
- Spring Cloud OpenFeign
- H2 Database

### Testing & Quality
- JUnit 5
- Mockito
- Integration Tests
- API Tests

### Development Tools
- Maven
- Lombok
- Spring DevTools

## Quick Start

1. Clone the repository:
```bash
git clone https://github.com/yourusername/BaseApiH2.git
cd BaseApiH2
 ```
2. Build the project:
```bash
mvn clean install
 ```

3. Run the application:
```bash
mvn spring-boot:run
 ```

The API will be available at http://localhost:8080

## Database Configuration
H2 Database configuration in application.yml:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:ratedb
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
 ```

Access H2 Console at: http://localhost:8080/h2-console

## API Documentation
### Available Endpoints Rate Management
- GET /rates/{id} - Get rate by ID
- GET /rates/search - Search rates by criteria
- POST /rates - Create new rate
- PATCH /rates/{id}/price - Update rate price
- DELETE /rates/{id} - Delete rate Currency Integration
- Automatic currency formatting
- Real-time exchange rate updates
- Support for multiple currencies
## Error Handling
The API implements comprehensive error handling:

- 400 Bad Request - Invalid input data
- 404 Not Found - Resource not found
- 409 Conflict - Rate period overlap
- 500 Internal Server Error - Server issues
## Testing
Run tests with:

```bash
# All tests
mvn test

# Specific test class
mvn test -Dtest=RateServiceImplTest
 ```

## Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/baseh2/baseapih2/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── dto/
│   │       └── client/
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── com/baseh2/baseapih2/
 ```

## License
This project is licensed under the MIT License

