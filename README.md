# User Aggregator Service

## Running the Application

1. Start the databases:
```
   docker-compose up -d
```

2. Run the application:
```
   ./mvnw spring-boot:run
```

## API

### GET /users

Retrieves users from all configured databases.

**Query Parameters:**

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| username | string | - | Filter by username (partial match) |
| name | string | - | Filter by name (partial match) |
| surname | string | - | Filter by surname (partial match) |
| sortBy | enum | ID | Sort field: `ID`, `USERNAME`, `NAME`, `SURNAME` |
| sortOrder | enum | ASC | Sort order: `ASC`, `DESC` |

**Example:** http://localhost:9090/users?name=John&sortBy=SURNAME&sortOrder=DESC

## API Documentation

Swagger UI: http://localhost:9090/swagger-ui/index.html