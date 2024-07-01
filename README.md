# FM6 Private Institution Membership Portal

A comprehensive web application for managing membership applications from private educational institutions. Built with Spring Boot, JWT authentication, and modern web technologies.

## �� Project Overview

This application digitizes and streamlines the entire membership application process for private training institutions, providing a centralized portal for institutions to submit and track their applications, and for FM6 administrators to manage and process these requests efficiently.

## 🚀 Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (Administrator, Institution Representative)
  - Secure password handling

- **Application Management**
  - Submit membership applications
  - Track application status
  - Document upload and management
  - Application review and approval workflow

- **Institution Management**
  - Institution profile management
  - Contact information updates
  - Document repository

- **Administrative Features**
  - Dashboard for administrators
  - Application review and approval
  - User management
  - System monitoring

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.3.1** - Main framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM framework
- **JWT** - Token-based authentication
- **Maven** - Build tool

### Database
- **H2 Database** - Development (in-memory)
- **SQL Server** - Production ready

### Frontend (Planned)
- **Angular** - Frontend framework
- **Bootstrap** - UI components
- **TypeScript** - Programming language

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- SQL Server (for production)
- Node.js (for frontend development)

## 🚀 Quick Start

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Youss2f/Adhesion.git
   cd Adhesion
   ```

2. **Configure database**
   - For development: H2 database is configured by default
   - For production: Update `application.properties` with SQL Server credentials

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080/api`
   - H2 Console: `http://localhost:8080/api/h2-console`
   - Health Check: `http://localhost:8080/api/health`

### Default Credentials

- **Administrator**
  - Email: `admin@fm6education.ma`
  - Password: `admin123`

- **Institution Representative**
  - Email: `institution@example.com`
  - Password: `password123`

## 📚 API Documentation

### Authentication Endpoints
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/me` - Get current user info

### Application Endpoints
- `GET /api/applications` - List applications
- `POST /api/applications` - Submit application
- `GET /api/applications/{id}` - Get application details
- `PUT /api/applications/{id}` - Update application
- `DELETE /api/applications/{id}` - Delete application

### User Management
- `GET /api/users` - List users (Admin only)
- `GET /api/users/{id}` - Get user details
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## 🔧 Configuration

### Database Configuration
```properties
# Development (H2)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver

# Production (SQL Server)
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=fm6_adhesion_db
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### JWT Configuration
```properties
jwt.secret=FM6EducationSecretKey2024ForJWTTokenGenerationAndValidation
jwt.expiration=86400000
```

## 🏗️ Project Structure

```
src/main/java/ma/fm6education/adhesion/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── exception/      # Exception handling
├── repository/     # Data access layer
├── security/       # Security configuration
└── service/        # Business logic
```

## 🧪 Testing

Run tests with Maven:
```bash
mvn test
```

## 📝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Youssef** - *Initial work* - [Youss2f](https://github.com/Youss2f)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- FM6 Education for the project requirements
- All contributors and reviewers

## 📞 Support

For support, email youssef.atertour.2@gmail.com or create an issue in this repository.

---

**Built with ❤️ for FM6 Education**
