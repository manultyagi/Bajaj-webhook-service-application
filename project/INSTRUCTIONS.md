# Build and Run Instructions

## Quick Start

1. **Prerequisites**
   - Ensure Java 17+ is installed: `java -version`
   - Ensure Maven 3.6+ is installed: `mvn -version`

2. **Build the Application**
   ```bash
   mvn clean package
   ```

3. **Run the Application**
   ```bash
   java -jar target/qualifier-task-1.0.0.jar
   ```

## Detailed Steps

### 1. Build Process
```bash
# Clean previous builds
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Package into JAR
mvn package
```

### 2. Verify Build
After running `mvn package`, you should see:
- `target/qualifier-task-1.0.0.jar` - The executable JAR file
- Build success message in the console

### 3. Execute
```bash
java -jar target/qualifier-task-1.0.0.jar
```

The application will:
1. Start up and display Spring Boot banner
2. Execute the qualifier task automatically
3. Show detailed logs of each step
4. Complete and shut down after sending the solution

### 4. Expected Output
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.1)

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

## Troubleshooting

### Java Version Issues
```bash
# Check Java version
java -version

# If wrong version, set JAVA_HOME
export JAVA_HOME=/path/to/java17
```

### Maven Issues
```bash
# Check Maven version
mvn -version

# Clean and retry if build fails
mvn clean
mvn package -X  # for debug output
```

### Network Issues
- Ensure internet connectivity
- Check if corporate firewalls block the API endpoints
- Verify the API endpoints are accessible

### Log Files
Check `logs/qualifier-task.log` for detailed execution logs if console output is unclear.

## File Outputs

After successful execution:
- **JAR File**: `target/qualifier-task-1.0.0.jar`
- **Log File**: `logs/qualifier-task.log`
- **Test Reports**: `target/surefire-reports/`

## GitHub Repository

To create a GitHub repository:

1. Initialize git:
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Bajaj Finserv Health Qualifier Task"
   ```

2. Create repository on GitHub and push:
   ```bash
   git remote add origin https://github.com/yourusername/bajaj-finserv-qualifier.git
   git branch -M main
   git push -u origin main
   ```

## Additional Notes

- The application automatically shuts down after completing the task
- All HTTP requests include proper error handling and logging
- The SQL solution is generated based on the registration number pattern
- Authentication uses JWT Bearer tokens as required by the API