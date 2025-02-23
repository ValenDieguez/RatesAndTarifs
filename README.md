# Rates and Tariffs Management API

A Spring Boot microservice that manages product rates and tariffs with dynamic currency support and price formatting. This service provides a comprehensive REST API for handling price rates across different products and brands.

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
- Cross-origin support for frontend integration (Angular)
- OpenAPI 3.0 documentation
- Comprehensive error handling
- Input validation and sanitization
- RESTful API design
- Caching for currency service
- Hexagonal architecture implementation
- Domain-Driven Design principles

## Technical Stack

### Core Technologies
- Java 17
- Spring Boot 3.2.2
- Spring Cloud OpenFeign
- H2 Database
- OpenAPI 3.0

### Testing & Quality
- JUnit 5
- Mockito
- Integration Tests
- API Tests
- Repository Tests

### Development Tools
- Maven
- Lombok
- Spring DevTools
- Swagger UI

## Quick Start

1. Clone the repository:
```bash
git clone https://github.com/yourusername/RatesAndTarifs.git
cd RatesAndTarifs
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

### Swagger/OpenAPI Documentation
The API documentation is available through Swagger UI and follows the OpenAPI 3.0 specification.

#### Accessing Swagger UI
Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

#### API Specification
- **Title**: Rates and Tariffs API
- **Description**: API for managing product rates and tariffs with dynamic currency support
- **Version**: 1.0.0
- **Base URL**: /api/rates

### Available Endpoints

#### Rate Management
1. **Get Rate by ID**
   - Method: GET
   - Path: `/api/rates/{id}`
   - Description: Retrieves a specific rate using its unique identifier
   - Responses:
     - 200: Rate found
     - 404: Rate not found

2. **Search Rates**
   - Method: GET
   - Path: `/api/rates/search`
   - Description: Find the applicable rate for a given product, brand and date
   - Parameters:
     - date (DateTime): Date and time for rate application
     - productId (Long): Product ID
     - brandId (Long): Brand ID
   - Responses:
     - 200: Applicable rate found
     - 404: No applicable rate found

3. **Create Rate**
   - Method: POST
   - Path: `/api/rates`
   - Description: Creates a new rate entry
   - Responses:
     - 201: Rate created successfully
     - 400: Invalid input

4. **Update Rate Price**
   - Method: PATCH
   - Path: `/api/rates/{id}/price`
   - Description: Updates the price of an existing rate
   - Responses:
     - 200: Price updated successfully
     - 404: Rate not found
     - 400: Invalid price value

5. **Delete Rate**
   - Method: DELETE
   - Path: `/api/rates/{id}`
   - Description: Deletes an existing rate
   - Responses:
     - 204: Rate deleted successfully
     - 404: Rate not found

### API Groups
The API endpoints are organized into the following group:
- **rates-public**: Contains all rate-related operations under `/api/rates/**`

### Error Handling
The API implements comprehensive error handling:

- 400 Bad Request - Invalid input data
- 404 Not Found - Resource not found
- 500 Internal Server Error - Server-side errors

### Contact Information
For any queries regarding the API:
- Name: Your Name
- Email: your.email@example.com

