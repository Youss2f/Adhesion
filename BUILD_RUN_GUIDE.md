# FM6 Membership Portal - Build, Run & Deployment Guide

## 🚀 Quick Start Guide

### Prerequisites
- **Java 17** or higher
- **Node.js 18** or higher
- **Microsoft SQL Server** (or SQL Server Express)
- **Maven 3.8+** (or use Maven wrapper)
- **Angular CLI 17+** (`npm install -g @angular/cli`)

## 📊 Database Setup

### 1. SQL Server Configuration
```sql
-- Create database
CREATE DATABASE fm6_membership;

-- Create login (if needed)
CREATE LOGIN fm6_user WITH PASSWORD = 'YourSecurePassword123!';

-- Create user and grant permissions
USE fm6_membership;
CREATE USER fm6_user FOR LOGIN fm6_user;
ALTER ROLE db_owner ADD MEMBER fm6_user;
```

### 2. Connection Configuration
Update `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=fm6_membership;encrypt=false;trustServerCertificate=true
    username: fm6_user
    password: YourSecurePassword123!
```

### 3. Initial Data (Optional)
Create `backend/src/main/resources/data.sql`:
```sql
-- Insert default admin user
INSERT INTO users (nom, prenom, email, mot_de_passe, role, actif, date_creation)
VALUES ('Admin', 'FM6', 'admin@fm6.ma', '$2a$10$8.6.EQhK2gHQNPjY7Qd1s.K9kJFHGhQEjvvlK6X8N6VJrYQHKjQRK', 'ADMIN', 1, GETDATE());
-- Password: admin123
```

## 🏗️ Backend Setup & Build

### 1. Navigate to Backend Directory
```powershell
cd backend
```

### 2. Build the Project
```powershell
# Using Maven wrapper (recommended)
./mvnw clean install

# Or using installed Maven
mvn clean install
```

### 3. Run the Application
```powershell
# Development mode (with hot reload)
./mvnw spring-boot:run

# Or run the built JAR
java -jar target/membership-portal-1.0.0.jar
```

### 4. Verify Backend
- **API Base URL**: `http://localhost:8080`
- **Health Check**: `http://localhost:8080/actuator/health` (if actuator is enabled)
- **Swagger UI**: `http://localhost:8080/swagger-ui.html` (if Swagger is configured)

## 🎨 Frontend Setup & Build

### 1. Navigate to Frontend Directory
```powershell
cd frontend
```

### 2. Install Dependencies
```powershell
npm install
```

### 3. Start Development Server
```powershell
# Development mode with hot reload
ng serve

# Or specify port
ng serve --port 4200
```

### 4. Build for Production
```powershell
# Production build
ng build --configuration production

# Output will be in dist/ directory
```

### 5. Verify Frontend
- **Application URL**: `http://localhost:4200`
- **Login Page**: `http://localhost:4200/auth/login`

## 📧 Email Configuration

### SMTP Setup (Gmail Example)
Update `backend/src/main/resources/application.yml`:
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password  # Use App Password, not regular password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

### Email Features
- **Registration Confirmation**: Sent when user registers
- **Application Confirmation**: Sent when application is submitted  
- **Status Notifications**: Sent when admin approves/rejects applications

### Testing Email (Development)
For development, you can use:
- **Mailhog**: Local SMTP testing server
- **Mailtrap**: Online email testing service
- **Console logging**: Set `spring.mail.host=` to disable actual sending

## 🧪 Testing Strategy

### Backend Testing
```powershell
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=AuthControllerTest

# Integration tests
./mvnw verify
```

### Frontend Testing
```powershell
# Unit tests
ng test

# E2E tests
ng e2e

# Test coverage
ng test --code-coverage
```

### Manual Testing Checklist
- [ ] User registration with valid/invalid data
- [ ] Login with correct/incorrect credentials
- [ ] Application submission by applicant
- [ ] Application review by admin
- [ ] Email notifications functionality
- [ ] Role-based access control
- [ ] File upload functionality
- [ ] Responsive design on mobile/tablet

## 🔐 Security Configuration

### Production Security Settings
```yaml
# application-prod.yml
fm6:
  app:
    jwtSecret: ${JWT_SECRET:your-very-long-secure-secret-key-for-production}
    jwtExpirationMs: ${JWT_EXPIRATION:3600000}  # 1 hour for production

spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
```

### Environment Variables
Set these environment variables in production:
```bash
JWT_SECRET=your-production-jwt-secret-key-must-be-very-long
DATABASE_URL=jdbc:sqlserver://your-server:1433;databaseName=fm6_membership
DATABASE_USERNAME=your-db-user
DATABASE_PASSWORD=your-db-password
MAIL_USERNAME=your-smtp-username
MAIL_PASSWORD=your-smtp-password
```

## 🚀 Deployment Options

### Option 1: Traditional Server Deployment
```powershell
# Build backend JAR
./mvnw clean package -DskipTests

# Build frontend
cd ../frontend
ng build --configuration production

# Copy files to server
# - backend JAR to server
# - frontend dist/ contents to web server (Apache/Nginx)
```

### Option 2: Docker Deployment
Create `Dockerfile` for backend:
```dockerfile
FROM openjdk:17-jre-slim
COPY target/membership-portal-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Create `Dockerfile` for frontend:
```dockerfile
FROM nginx:alpine
COPY dist/fm6-membership-frontend /usr/share/nginx/html
EXPOSE 80
```

### Option 3: Cloud Deployment
- **Azure App Service**: Deploy both backend and frontend
- **AWS Elastic Beanstalk**: Spring Boot application
- **Heroku**: Easy deployment with Git integration

## 📋 Development Workflow

### Daily Development
1. **Start Backend**: `./mvnw spring-boot:run`
2. **Start Frontend**: `ng serve`
3. **Database**: Ensure SQL Server is running
4. **Testing**: Run tests before committing

### Code Quality
```powershell
# Backend: Check code style
./mvnw checkstyle:check

# Frontend: Lint code
ng lint

# Frontend: Format code
ng format
```

## 🔧 Troubleshooting

### Common Issues

#### Backend Issues
- **Port 8080 in use**: Change port in `application.yml` or kill process
- **Database connection**: Verify SQL Server is running and credentials are correct
- **JWT errors**: Check secret key length (must be at least 256 bits)

#### Frontend Issues
- **CORS errors**: Ensure backend CORS configuration allows frontend origin
- **Module not found**: Run `npm install` to install dependencies
- **Build errors**: Check TypeScript version compatibility

#### Database Issues
- **Connection timeout**: Check firewall and SQL Server network configuration
- **Authentication failed**: Verify user permissions and password

### Debug Mode
```yaml
# Enable debug logging
logging:
  level:
    ma.fm6.membership: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
```

## 📈 Performance Optimization

### Backend Optimization
- **Database indexing**: Add indexes on frequently queried columns
- **Connection pooling**: Configure HikariCP properly
- **Caching**: Add Spring Cache for frequently accessed data

### Frontend Optimization
- **Lazy loading**: Implement route-based lazy loading
- **AOT compilation**: Use `--aot` flag for production builds
- **Bundle analysis**: Use `ng build --stats-json` and webpack-bundle-analyzer

## 🛡️ Security Best Practices

### Implementation Checklist
- [ ] HTTPS in production
- [ ] Strong JWT secret key
- [ ] Password complexity requirements
- [ ] Rate limiting on authentication endpoints
- [ ] Input validation and sanitization
- [ ] SQL injection prevention (JPA handles this)
- [ ] XSS protection (Angular handles this)
- [ ] CSRF protection disabled for stateless API

## 📚 Additional Features to Implement

### Phase 2 Enhancements
1. **File Upload**: Document management for applications
2. **Audit Trail**: Track all user actions
3. **Advanced Search**: Full-text search capabilities
4. **Reporting**: Export applications to PDF/Excel
5. **Multi-language**: French/Arabic language support
6. **Mobile App**: React Native or Progressive Web App
7. **API Documentation**: Swagger/OpenAPI integration

### Monitoring and Analytics
- **Application logging**: Structured logging with correlation IDs
- **Performance monitoring**: APM tools integration
- **User analytics**: Track user behavior and application metrics
- **Health checks**: Comprehensive health endpoints

This completes the comprehensive setup and deployment guide for the FM6 Membership Portal!