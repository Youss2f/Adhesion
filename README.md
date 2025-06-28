# FM6 Private Institution Membership Portal

A comprehensive web application for managing membership applications from private training institutions to the Mohammed VI Foundation for the Promotion of Social Works in Education and Training (FM6).

## 🎯 Project Overview

This application digitizes and streamlines the entire membership application process for private training institutions, providing a centralized portal for institutions to submit and track their applications, and for FM6 administrators to manage and process these requests efficiently.

## 🏗️ Architecture

The application follows a **3-Tier Client-Server Architecture**:

- **Frontend**: Angular 18 (TypeScript, HTML5, CSS3)
- **Backend**: Spring Boot 3.3.1 (Java 17)
- **Database**: Microsoft SQL Server
- **Authentication**: JWT (JSON Web Tokens)

## 🚀 Features

### For Institution Representatives
- ✅ User registration and authentication
- ✅ Submit membership applications with required documents
- ✅ Track application status in real-time
- ✅ View application history
- ✅ Receive email notifications on status changes

### For Foundation Administrators
- ✅ Secure admin dashboard
- ✅ Review and manage all applications
- ✅ Approve or reject applications with notes
- ✅ Filter applications by status
- ✅ View application statistics
- ✅ Manage user accounts

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Node.js 18** or higher
- **Angular CLI 18**
- **Microsoft SQL Server** (or SQL Server Express)
- **Maven 3.6** or higher

## 🛠️ Installation & Setup

### 1. Database Setup

1. Install Microsoft SQL Server
2. Create a new database named `fm6_adhesion_db`
3. Update database credentials in `adhesion/src/main/resources/application.properties`

### 2. Backend Setup

```bash
# Navigate to the backend directory
cd adhesion

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

```bash
# Navigate to the frontend directory
cd adhesion-frontend

# Install dependencies
npm install

# Start the development server
ng serve
```

The frontend will start on `http://localhost:4200`

## 📁 Project Structure

```
adhesion/                          # Backend (Spring Boot)
├── src/main/java/ma/fm6education/adhesion/
│   ├── config/                   # Configuration classes
│   ├── controller/               # REST API controllers
│   ├── dto/                      # Data Transfer Objects
│   ├── entity/                   # JPA entities
│   ├── repository/               # Data access layer
│   ├── security/                 # Security configuration
│   └── service/                  # Business logic layer
├── src/main/resources/
│   └── application.properties    # Application configuration
└── pom.xml                       # Maven dependencies

adhesion-frontend/                # Frontend (Angular)
├── src/app/
│   ├── components/               # Angular components
│   ├── services/                 # Angular services
│   ├── models/                   # TypeScript interfaces
│   └── guards/                   # Route guards
├── src/assets/                   # Static assets
└── package.json                  # Node.js dependencies
```

## 🔐 Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Hashing**: BCrypt password encryption
- **Role-based Access Control**: Different permissions for users and admins
- **CORS Configuration**: Secure cross-origin requests
- **Input Validation**: Comprehensive data validation

## 📊 API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `GET /api/auth/validate` - Token validation

### Applications
- `POST /api/applications` - Submit new application
- `GET /api/applications/my-applications` - Get user's applications
- `GET /api/applications/{id}` - Get specific application
- `GET /api/applications/admin/all` - Get all applications (admin)
- `PUT /api/applications/{id}/approve` - Approve application (admin)
- `PUT /api/applications/{id}/reject` - Reject application (admin)
- `GET /api/applications/admin/stats` - Get application statistics (admin)

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `email` (Unique)
- `password` (Hashed)
- `full_name`
- `role` (INSTITUTION_REPRESENTATIVE, ADMINISTRATOR)
- `institution_name`
- `institution_address`
- `phone_number`
- `is_active`
- `created_at`
- `updated_at`

### Applications Table
- `id` (Primary Key)
- `user_id` (Foreign Key)
- `establishment_name`
- `establishment_address`
- `contact_person_name`
- `contact_email`
- `contact_phone`
- `establishment_type`
- `number_of_employees`
- `years_of_operation`
- `business_license_number`
- `tax_registration_number`
- `status` (EN_ATTENTE, VALIDEE, REJETEE)
- `submission_date`
- `review_date`
- `review_notes`

### Documents Table
- `id` (Primary Key)
- `application_id` (Foreign Key)
- `document_name`
- `file_name`
- `file_path`
- `file_size`
- `content_type`
- `document_type`
- `upload_date`

## 🧪 Testing

### Backend Testing
```bash
cd adhesion
mvn test
```

### Frontend Testing
```bash
cd adhesion-frontend
ng test
```

## 🚀 Deployment

### Production Build

#### Backend
```bash
cd adhesion
mvn clean package
java -jar target/adhesion-0.0.1-SNAPSHOT.jar
```

#### Frontend
```bash
cd adhesion-frontend
ng build --configuration production
```

## 🔧 Configuration

### Environment Variables

Create a `.env` file in the backend directory:

```properties
# Database
DB_URL=jdbc:sqlserver://localhost:1433;databaseName=fm6_adhesion_db
DB_USERNAME=your_username
DB_PASSWORD=your_password

# JWT
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000

# Email (for future use)
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password
```

## 📝 API Documentation

### Authentication Flow

1. **Registration**: User registers with institution details
2. **Login**: User logs in and receives JWT token
3. **Token Usage**: Include token in Authorization header for protected endpoints
4. **Token Validation**: Server validates token on each request

### Application Status Flow

1. **EN_ATTENTE** (Pending): Initial status when application is submitted
2. **VALIDEE** (Approved): Application approved by administrator
3. **REJETEE** (Rejected): Application rejected by administrator

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **ATERTOUR Youssef** - *Initial work* - [GitHub Profile]

## 🙏 Acknowledgments

- Mohammed VI Foundation for the Promotion of Social Works in Education and Training (FM6)
- Spring Boot team for the excellent framework
- Angular team for the powerful frontend framework

## 📞 Support

For support and questions, please contact:
- Email: support@fm6education.ma
- Phone: +212-XXX-XXXXXX

## 🔄 Version History

- **v1.0.0** - Initial release with core functionality
  - User authentication and registration
  - Application submission and management
  - Admin dashboard
  - JWT security implementation

---

**Note**: This application is designed for the specific needs of FM6 and private training institutions in Morocco. Please ensure compliance with local data protection regulations and institutional policies. # Adhesion
