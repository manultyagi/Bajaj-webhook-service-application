# Bajaj Finserv Health Qualifier Task - Spring Boot Application

This Spring Boot application automatically completes the Bajaj Finserv Health qualifier task by:

1. Sending a POST request to generate a webhook URL and access token
2. Determining the appropriate question based on registration number
3. Solving the SQL problem and sending the solution to the webhook URL

## Project Structure

```
src/
├── main/
│   ├── java/com/bajajfinservhealth/qualifiertask/
│   │   ├── QualifierTaskApplication.java     # Main Spring Boot application
│   │   ├── config/
│   │   │   └── RestTemplateConfig.java       # REST client configuration
│   │   ├── model/
│   │   │   ├── WebhookRequest.java           # Request model for webhook generation
│   │   │   ├── WebhookResponse.java          # Response model from webhook API
│   │   │   └── SolutionRequest.java          # Request model for solution submission
│   │   ├── runner/
│   │   │   └── QualifierTaskRunner.java      # CommandLineRunner for automatic execution
│   │   └── service/
│   │       ├── WebhookService.java           # HTTP client service for API calls
│   │       └── SqlQueryService.java          # SQL query generation service
│   └── resources/
│       └── application.yml                   # Application configuration
└── test/
    └── java/com/bajajfinservhealth/qualifiertask/
        ├── QualifierTaskApplicationTests.java
        └── service/
            └── SqlQueryServiceTest.java
```

## Features

- **Automatic Execution**: Runs the qualifier task automatically on application startup
- **HTTP Client Integration**: Uses RestTemplate for API communication
- **Comprehensive Logging**: Detailed logging for debugging and monitoring
- **Error Handling**: Robust error handling with proper exception management
- **Clean Architecture**: Well-structured code with separation of concerns
- **Unit Tests**: Test coverage for core functionality

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Internet connection for API calls

## Building and Running

### 1. Build the Application

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package into JAR
mvn clean package
```

### 2. Run the Application

#### Option A: Using Maven
```bash
mvn spring-boot:run
```

#### Option B: Using JAR file
```bash
java -jar target/qualifier-task-1.0.0.jar
```

#### Option C: Using IDE
Import the project into your IDE (IntelliJ IDEA, Eclipse, VS Code) and run the `QualifierTaskApplication` class.

## Expected Output

When the application starts successfully, you should see:

```
================================================================================
Starting Bajaj Finserv Health Qualifier Task
================================================================================
Step 1: Generating webhook and retrieving credentials...
✓ Successfully generated webhook
Step 2: Determining question based on registration number...
✓ Generated SQL solution
Step 3: Sending solution to webhook...
✓ Successfully completed qualifier task!
================================================================================
QUALIFIER TASK COMPLETED SUCCESSFULLY
================================================================================
```

## Configuration

The application uses the following configuration (in `application.yml`):

- **Server Port**: 8080 (though not used for external requests)
- **Logging Level**: INFO for application, DEBUG for HTTP client
- **Log File**: `logs/qualifier-task.log`

## SQL Solution Logic

The application determines which question to solve based on the last two digits of the registration number:

- **Odd last two digits (like "47")**: Solves Question 1
- **Even last two digits**: Would solve Question 2

For regNo "REG12347" (ending in 47, which is odd), it generates a SQL query that:
- Joins employees and departments tables
- Finds employees earning above their department average
- Ranks employees by salary within each department
- Orders results by department and salary

## Error Handling

The application includes comprehensive error handling:

- Network connectivity issues
- API response validation
- JSON parsing errors
- Authentication failures
- Graceful degradation with detailed logging

## Testing

Run the test suite:

```bash
mvn test
```

The tests cover:
- Application context loading
- SQL query generation logic
- Service layer functionality

## Logs

Application logs are written to:
- **Console**: Real-time output with timestamps
- **File**: `logs/qualifier-task.log` for persistent storage

## Deployment

### Creating Deployable JAR

```bash
mvn clean package
```

The JAR file will be created at: `target/qualifier-task-1.0.0.jar`

### Docker Deployment (Optional)

Create a `Dockerfile`:

```dockerfile
FROM openjdk:17-jre-slim
COPY target/qualifier-task-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t qualifier-task .
docker run qualifier-task
```

## Troubleshooting

### Common Issues

1. **Connection Issues**: Verify internet connectivity and API endpoint availability
2. **Authentication Errors**: Check if the API is returning valid access tokens
3. **JSON Parsing**: Ensure the API response format matches expected models

### Debug Mode

To enable more detailed logging, update `application.yml`:

```yml
logging:
  level:
    com.bajajfinservhealth.qualifiertask: DEBUG
    org.springframework.web.client: TRACE
```

## Contact

For questions or issues related to this qualifier task implementation, please refer to the Bajaj Finserv Health hiring team.