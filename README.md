# 🏛️ FM6 Private Institution Membership Portal

[![CI/CD Pipeline](https://github.com/Youss2f/Adhesion/actions/workflows/ci.yml/badge.svg)](https://github.com/Youss2f/Adhesion/actions/workflows/ci.yml)
[![Code Coverage](https://codecov.io/gh/Youss2f/Adhesion/branch/main/graph/badge.svg)](https://codecov.io/gh/Youss2f/Adhesion)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Youss2f_Adhesion&metric=security_rating)](https://sonarcloud.io/dashboard?id=Youss2f_Adhesion)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A comprehensive web application for managing private institution membership applications to FM6 (Fédération Marocaine des Écoles Privées - 6e Région).

## 🌟 Features

### 🔐 Authentication & Security
- **JWT-based authentication** with Spring Security
- **Role-based access control** (Admin, User, Moderator)
- **Secure password encryption** with BCrypt
- **Token refresh mechanism** for seamless user experience

### 📝 Application Management
- **User registration** with email verification
- **Membership application submission** with document upload
- **Real-time application status tracking**
- **Email notifications** for status updates

### 👑 Administrative Features
- **Application review dashboard** for administrators
- **Bulk application processing** capabilities
- **User management** and role assignment
- **Statistical reporting** and analytics

### 🎨 Modern User Interface
- **Responsive design** with Angular Material
- **Progressive Web App** capabilities
- **Real-time updates** with WebSocket integration
- **Multilingual support** (French/Arabic)

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework:** Spring Boot 3.2.0 with Java 17
- **Security:** Spring Security + JWT
- **Database:** JPA/Hibernate with PostgreSQL
- **API:** RESTful services with OpenAPI 3.0
- **Testing:** JUnit 5 + Mockito + Testcontainers

### Frontend (Angular)
- **Framework:** Angular 17+ with TypeScript
- **UI Library:** Angular Material + Bootstrap
- **State Management:** RxJS Services
- **Testing:** Jasmine + Karma + Cypress
- **Build:** Angular CLI with PWA support

### Infrastructure
- **Containerization:** Docker + Docker Compose
- **CI/CD:** GitHub Actions with automated testing
- **Monitoring:** Actuator + Micrometer metrics
- **Security:** OWASP dependency scanning

## 🚀 Quick Start

### Prerequisites
- **Java 17+**
- **Node.js 18+**
- **Docker & Docker Compose**
- **PostgreSQL 15+** (or use Docker)

### 1. Clone the Repository
```bash
git clone https://github.com/Youss2f/Adhesion.git
cd Adhesion
```

### 2. Environment Setup
```bash
# Copy environment template
cp .env.example .env

# Configure your database and email settings
# Edit .env file with your specific configurations
```

### 3. Docker Development (Recommended)
```bash
# Start all services
docker-compose up -d

# Access the application
# Frontend: http://localhost:80
# Backend API: http://localhost:8080
# Database: localhost:5432
```

### 4. Manual Setup

#### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend
```bash
cd frontend
npm install
ng serve
```

## 📊 Testing

### Backend Testing
```bash
cd backend
mvn test                    # Unit tests
mvn verify                  # Integration tests
mvn jacoco:report          # Coverage report
```

### Frontend Testing
```bash
cd frontend
npm test                    # Unit tests
npm run test:coverage      # Coverage report
npm run e2e                # End-to-end tests
```

### Full Test Suite
```bash
# Run all tests with coverage
docker-compose -f docker-compose.test.yml up --abort-on-container-exit
```

## 📖 API Documentation

The API documentation is available at:
- **Development:** http://localhost:8080/swagger-ui.html
- **OpenAPI Spec:** http://localhost:8080/v3/api-docs

### Key Endpoints
```bash
POST /api/auth/signin          # User authentication
POST /api/auth/signup          # User registration
GET  /api/applications         # List applications (authenticated)
POST /api/applications         # Submit new application
GET  /api/admin/applications   # Admin application management
```

## 🛠️ Development

### Code Style
- **Backend:** Google Java Style Guide
- **Frontend:** Angular Style Guide + ESLint + Prettier
- **Commits:** Conventional Commits specification

### Database Migrations
```bash
# Generate migration (after entity changes)
mvn flyway:migrate

# Rollback migration
mvn flyway:undo
```

### Building for Production
```bash
# Build both services
docker-compose -f docker-compose.prod.yml build

# Deploy to production
docker-compose -f docker-compose.prod.yml up -d
```

## 🔧 Configuration

### Environment Variables
| Variable | Description | Default |
|----------|-------------|---------|
| `DATABASE_URL` | PostgreSQL connection string | `jdbc:postgresql://localhost:5432/membership` |
| `JWT_SECRET` | Secret key for JWT tokens | `mySecretKey` |
| `JWT_EXPIRATION` | Token expiration time (seconds) | `86400` |
| `EMAIL_HOST` | SMTP server host | `localhost` |
| `EMAIL_PORT` | SMTP server port | `587` |

### Profiles
- **Development:** `spring.profiles.active=dev`
- **Testing:** `spring.profiles.active=test`
- **Production:** `spring.profiles.active=prod`

## 📈 Monitoring & Analytics

### Health Checks
- **Backend:** http://localhost:8080/actuator/health
- **Frontend:** http://localhost:80/health
- **Database:** Included in Docker health checks

### Metrics
- **Application metrics:** Micrometer + Prometheus
- **Custom business metrics:** Application KPIs
- **Infrastructure monitoring:** Docker stats

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

### Development Workflow
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Add tests for your changes
5. Ensure all tests pass
6. Commit your changes (`git commit -m 'feat: add amazing feature'`)
7. Push to the branch (`git push origin feature/amazing-feature`)
8. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Team

- **Project Lead:** [Youssef](https://github.com/Youss2f)
- **Backend Development:** Spring Boot Team
- **Frontend Development:** Angular Team
- **DevOps & Infrastructure:** Platform Team

## 🙏 Acknowledgments

- **FM6** for the opportunity to digitize their membership process
- **Spring Boot** and **Angular** communities for excellent frameworks
- **Open Source** contributors who make projects like this possible

---

<div align="center">
  <p>Built with ❤️ for FM6 Private Institution Community</p>
  <p>
    <a href="https://github.com/Youss2f/Adhesion">🌟 Star this repo</a> •
    <a href="https://github.com/Youss2f/Adhesion/issues">🐛 Report Bug</a> •
    <a href="https://github.com/Youss2f/Adhesion/issues">💡 Request Feature</a>
  </p>
</div>