# FM6 Private Institution Membership Portal - API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## Endpoints

### 1. Authentication Endpoints

#### 1.1 User Registration
**POST** `/auth/register`

Register a new institution representative.

**Request Body:**
```json
{
  "email": "institution@example.ma",
  "password": "securePassword123",
  "fullName": "John Doe",
  "institutionName": "Example Training Center",
  "institutionAddress": "123 Main Street, Casablanca, Morocco",
  "phoneNumber": "+212-5-22-123456"
}
```

**Response:**
```json
{
  "message": "User registered successfully",
  "userId": 1,
  "email": "institution@example.ma"
}
```

#### 1.2 User Login
**POST** `/auth/login`

Authenticate user and receive JWT token.

**Request Body:**
```json
{
  "email": "institution@example.ma",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "email": "institution@example.ma",
  "fullName": "John Doe",
  "role": "INSTITUTION_REPRESENTATIVE",
  "institutionName": "Example Training Center"
}
```

#### 1.3 User Logout
**POST** `/auth/logout`

Logout user (clears server-side session).

**Response:**
```json
{
  "message": "Logged out successfully"
}
```

#### 1.4 Token Validation
**GET** `/auth/validate`

Validate JWT token and return user information.

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Response:**
```json
{
  "valid": true,
  "userId": 1,
  "email": "institution@example.ma",
  "role": "INSTITUTION_REPRESENTATIVE",
  "fullName": "John Doe",
  "institutionName": "Example Training Center"
}
```

### 2. Application Endpoints

#### 2.1 Submit Application
**POST** `/applications`

Submit a new membership application.

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Request Body:**
```json
{
  "establishmentName": "Example Training Center",
  "establishmentAddress": "123 Main Street, Casablanca, Morocco",
  "contactPersonName": "John Doe",
  "contactEmail": "contact@example.ma",
  "contactPhone": "+212-5-22-123456",
  "establishmentType": "Private Training Center",
  "numberOfEmployees": 25,
  "yearsOfOperation": 5,
  "businessLicenseNumber": "LIC-2024-001",
  "taxRegistrationNumber": "TAX-2024-001"
}
```

**Response:**
```json
{
  "message": "Application submitted successfully",
  "application": {
    "id": 1,
    "establishmentName": "Example Training Center",
    "establishmentAddress": "123 Main Street, Casablanca, Morocco",
    "contactPersonName": "John Doe",
    "contactEmail": "contact@example.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001",
    "status": "EN_ATTENTE",
    "submissionDate": "2024-01-15T10:30:00",
    "userId": 1,
    "userEmail": "institution@example.ma"
  }
}
```

#### 2.2 Get User's Applications
**GET** `/applications/my-applications`

Get all applications submitted by the authenticated user.

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Response:**
```json
[
  {
    "id": 1,
    "establishmentName": "Example Training Center",
    "establishmentAddress": "123 Main Street, Casablanca, Morocco",
    "contactPersonName": "John Doe",
    "contactEmail": "contact@example.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001",
    "status": "EN_ATTENTE",
    "submissionDate": "2024-01-15T10:30:00",
    "userId": 1,
    "userEmail": "institution@example.ma"
  }
]
```

#### 2.3 Get Application by ID
**GET** `/applications/{id}`

Get a specific application by ID.

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

**Response:**
```json
{
  "id": 1,
  "establishmentName": "Example Training Center",
  "establishmentAddress": "123 Main Street, Casablanca, Morocco",
  "contactPersonName": "John Doe",
  "contactEmail": "contact@example.ma",
  "contactPhone": "+212-5-22-123456",
  "establishmentType": "Private Training Center",
  "numberOfEmployees": 25,
  "yearsOfOperation": 5,
  "businessLicenseNumber": "LIC-2024-001",
  "taxRegistrationNumber": "TAX-2024-001",
  "status": "EN_ATTENTE",
  "submissionDate": "2024-01-15T10:30:00",
  "userId": 1,
  "userEmail": "institution@example.ma"
}
```

### 3. Administrator Endpoints

#### 3.1 Get All Applications (Admin)
**GET** `/applications/admin/all`

Get all applications in the system (admin only).

**Headers:**
```
Authorization: Bearer <admin-jwt-token>
```

**Query Parameters:**
- `page` (optional): Page number (default: 0)
- `size` (optional): Page size (default: 10)
- `status` (optional): Filter by status (EN_ATTENTE, VALIDEE, REJETEE)

**Response:**
```json
[
  {
    "id": 1,
    "establishmentName": "Example Training Center",
    "establishmentAddress": "123 Main Street, Casablanca, Morocco",
    "contactPersonName": "John Doe",
    "contactEmail": "contact@example.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001",
    "status": "EN_ATTENTE",
    "submissionDate": "2024-01-15T10:30:00",
    "userId": 1,
    "userEmail": "institution@example.ma"
  }
]
```

#### 3.2 Approve Application (Admin)
**PUT** `/applications/{id}/approve`

Approve an application (admin only).

**Headers:**
```
Authorization: Bearer <admin-jwt-token>
```

**Request Body (optional):**
```json
{
  "reviewNotes": "Application approved after thorough review"
}
```

**Response:**
```json
{
  "message": "Application approved successfully",
  "application": {
    "id": 1,
    "establishmentName": "Example Training Center",
    "establishmentAddress": "123 Main Street, Casablanca, Morocco",
    "contactPersonName": "John Doe",
    "contactEmail": "contact@example.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001",
    "status": "VALIDEE",
    "submissionDate": "2024-01-15T10:30:00",
    "reviewDate": "2024-01-16T14:30:00",
    "reviewNotes": "Application approved after thorough review",
    "userId": 1,
    "userEmail": "institution@example.ma"
  }
}
```

#### 3.3 Reject Application (Admin)
**PUT** `/applications/{id}/reject`

Reject an application (admin only).

**Headers:**
```
Authorization: Bearer <admin-jwt-token>
```

**Request Body (optional):**
```json
{
  "reviewNotes": "Application rejected due to incomplete documentation"
}
```

**Response:**
```json
{
  "message": "Application rejected",
  "application": {
    "id": 1,
    "establishmentName": "Example Training Center",
    "establishmentAddress": "123 Main Street, Casablanca, Morocco",
    "contactPersonName": "John Doe",
    "contactEmail": "contact@example.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001",
    "status": "REJETEE",
    "submissionDate": "2024-01-15T10:30:00",
    "reviewDate": "2024-01-16T14:30:00",
    "reviewNotes": "Application rejected due to incomplete documentation",
    "userId": 1,
    "userEmail": "institution@example.ma"
  }
}
```

#### 3.4 Get Application Statistics (Admin)
**GET** `/applications/admin/stats`

Get application statistics (admin only).

**Headers:**
```
Authorization: Bearer <admin-jwt-token>
```

**Response:**
```json
{
  "pending": 5,
  "approved": 12,
  "rejected": 3,
  "total": 20
}
```

## Error Responses

### 400 Bad Request
```json
{
  "error": "Invalid email or password"
}
```

### 401 Unauthorized
```json
{
  "error": "Invalid token"
}
```

### 403 Forbidden
```json
{
  "error": "Access denied"
}
```

### 404 Not Found
```json
{
  "error": "Application not found"
}
```

### 500 Internal Server Error
```json
{
  "error": "An unexpected error occurred"
}
```

## Data Models

### User
```json
{
  "id": 1,
  "email": "user@example.ma",
  "fullName": "John Doe",
  "role": "INSTITUTION_REPRESENTATIVE",
  "institutionName": "Example Training Center",
  "institutionAddress": "123 Main Street, Casablanca, Morocco",
  "phoneNumber": "+212-5-22-123456",
  "isActive": true,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Application
```json
{
  "id": 1,
  "establishmentName": "Example Training Center",
  "establishmentAddress": "123 Main Street, Casablanca, Morocco",
  "contactPersonName": "John Doe",
  "contactEmail": "contact@example.ma",
  "contactPhone": "+212-5-22-123456",
  "establishmentType": "Private Training Center",
  "numberOfEmployees": 25,
  "yearsOfOperation": 5,
  "businessLicenseNumber": "LIC-2024-001",
  "taxRegistrationNumber": "TAX-2024-001",
  "status": "EN_ATTENTE",
  "submissionDate": "2024-01-15T10:30:00",
  "reviewDate": null,
  "reviewNotes": null,
  "userId": 1,
  "userEmail": "user@example.ma"
}
```

## Status Codes

- `EN_ATTENTE` - Application is pending review
- `VALIDEE` - Application has been approved
- `REJETEE` - Application has been rejected

## Rate Limiting

Currently, there are no rate limits implemented. However, it's recommended to implement rate limiting for production use.

## Security Considerations

1. **JWT Tokens**: Tokens expire after 24 hours
2. **Password Hashing**: All passwords are hashed using BCrypt
3. **CORS**: Configured to allow requests from authorized origins
4. **Input Validation**: All inputs are validated using Bean Validation
5. **Role-based Access**: Different endpoints require different user roles

## Testing

You can test the API using tools like:
- Postman
- cURL
- Insomnia
- Any HTTP client

### Sample cURL Commands

#### Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@institution.ma",
    "password": "password123",
    "fullName": "Test User",
    "institutionName": "Test Institution",
    "institutionAddress": "123 Test Street, Casablanca, Morocco",
    "phoneNumber": "+212-5-22-123456"
  }'
```

#### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@institution.ma",
    "password": "password123"
  }'
```

#### Submit Application
```bash
curl -X POST http://localhost:8080/api/applications \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "establishmentName": "Test Training Center",
    "establishmentAddress": "123 Test Street, Casablanca, Morocco",
    "contactPersonName": "Test User",
    "contactEmail": "test@institution.ma",
    "contactPhone": "+212-5-22-123456",
    "establishmentType": "Private Training Center",
    "numberOfEmployees": 25,
    "yearsOfOperation": 5,
    "businessLicenseNumber": "LIC-2024-001",
    "taxRegistrationNumber": "TAX-2024-001"
  }'
``` 