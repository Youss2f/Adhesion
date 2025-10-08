# Fondation Mohammed VI Membership Portal - Project Structure

## Overview

This project is a comprehensive web application developed for **Fondation Mohammed VI de Promotion des Œuvres Sociales de l'Education-Formation** to digitalize and streamline the membership application process for private training institutions.

### Mission Context
The Foundation is expanding its social services beyond public sector education personnel to include employees of private training institutions. This expansion necessitates a modern, secure, and efficient digital platform to handle the increased operational complexity.

## Root Directory Structure
```
fm6-membership-portal/
├── backend/                          # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── ma/
│   │   │   │       └── fm6/
│   │   │   │           └── membership/
│   │   │   │               ├── MembershipPortalApplication.java
│   │   │   │               ├── config/
│   │   │   │               │   ├── SecurityConfig.java
│   │   │   │               │   ├── JwtConfig.java
│   │   │   │               │   └── CorsConfig.java
│   │   │   │               ├── controller/
│   │   │   │               │   ├── AuthController.java
│   │   │   │               │   ├── ApplicationController.java
│   │   │   │               │   └── AdminController.java
│   │   │   │               ├── dto/
│   │   │   │               │   ├── request/
│   │   │   │               │   │   ├── LoginRequest.java
│   │   │   │               │   │   ├── RegisterRequest.java
│   │   │   │               │   │   └── ApplicationRequest.java
│   │   │   │               │   └── response/
│   │   │   │               │       ├── JwtResponse.java
│   │   │   │               │       ├── MessageResponse.java
│   │   │   │               │       └── ApplicationResponse.java
│   │   │   │               ├── entity/
│   │   │   │               │   ├── User.java
│   │   │   │               │   ├── Etablissement.java
│   │   │   │               │   └── DemandeAdhesion.java
│   │   │   │               ├── enums/
│   │   │   │               │   ├── Role.java
│   │   │   │               │   └── StatutDemande.java
│   │   │   │               ├── exception/
│   │   │   │               │   ├── GlobalExceptionHandler.java
│   │   │   │               │   └── CustomExceptions.java
│   │   │   │               ├── repository/
│   │   │   │               │   ├── UserRepository.java
│   │   │   │               │   ├── EtablissementRepository.java
│   │   │   │               │   └── DemandeAdhesionRepository.java
│   │   │   │               ├── security/
│   │   │   │               │   ├── JwtUtils.java
│   │   │   │               │   ├── JwtAuthEntryPoint.java
│   │   │   │               │   ├── JwtAuthTokenFilter.java
│   │   │   │               │   └── UserDetailsImpl.java
│   │   │   │               └── service/
│   │   │   │                   ├── AuthService.java
│   │   │   │                   ├── ApplicationService.java
│   │   │   │                   ├── UserService.java
│   │   │   │                   └── EmailService.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── application-dev.yml
│   │   │       ├── application-prod.yml
│   │   │       └── data.sql
│   │   └── test/
│   │       └── java/
│   │           └── ma/
│   │               └── fm6/
│   │                   └── membership/
│   │                       ├── controller/
│   │                       ├── service/
│   │                       └── integration/
│   ├── target/
│   ├── uploads/
│   │   └── documents/
│   ├── pom.xml
│   ├── .gitignore
│   └── README.md
│
├── frontend/                         # Angular Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── app-routing.module.ts
│   │   │   ├── app.component.ts
│   │   │   ├── app.component.html
│   │   │   ├── app.component.scss
│   │   │   ├── app.module.ts
│   │   │   ├── core/
│   │   │   │   ├── guards/
│   │   │   │   │   ├── auth.guard.ts
│   │   │   │   │   └── admin.guard.ts
│   │   │   │   ├── interceptors/
│   │   │   │   │   ├── jwt.interceptor.ts
│   │   │   │   │   └── error.interceptor.ts
│   │   │   │   ├── models/
│   │   │   │   │   ├── user.model.ts
│   │   │   │   │   ├── etablissement.model.ts
│   │   │   │   │   ├── application.model.ts
│   │   │   │   │   └── auth.model.ts
│   │   │   │   └── services/
│   │   │   │       ├── auth.service.ts
│   │   │   │       ├── application.service.ts
│   │   │   │       ├── user.service.ts
│   │   │   │       └── storage.service.ts
│   │   │   ├── features/
│   │   │   │   ├── auth/
│   │   │   │   │   ├── login/
│   │   │   │   │   │   ├── login.component.ts
│   │   │   │   │   │   ├── login.component.html
│   │   │   │   │   │   └── login.component.scss
│   │   │   │   │   ├── register/
│   │   │   │   │   │   ├── register.component.ts
│   │   │   │   │   │   ├── register.component.html
│   │   │   │   │   │   └── register.component.scss
│   │   │   │   │   └── auth-routing.module.ts
│   │   │   │   ├── applicant/
│   │   │   │   │   ├── dashboard/
│   │   │   │   │   │   ├── dashboard.component.ts
│   │   │   │   │   │   ├── dashboard.component.html
│   │   │   │   │   │   └── dashboard.component.scss
│   │   │   │   │   ├── application-form/
│   │   │   │   │   │   ├── application-form.component.ts
│   │   │   │   │   │   ├── application-form.component.html
│   │   │   │   │   │   └── application-form.component.scss
│   │   │   │   │   └── applicant-routing.module.ts
│   │   │   │   └── admin/
│   │   │   │       ├── dashboard/
│   │   │   │       │   ├── admin-dashboard.component.ts
│   │   │   │       │   ├── admin-dashboard.component.html
│   │   │   │       │   └── admin-dashboard.component.scss
│   │   │   │       ├── application-review/
│   │   │   │       │   ├── application-review.component.ts
│   │   │   │       │   ├── application-review.component.html
│   │   │   │       │   └── application-review.component.scss
│   │   │   │       ├── user-management/
│   │   │   │       │   ├── user-management.component.ts
│   │   │   │       │   ├── user-management.component.html
│   │   │   │       │   └── user-management.component.scss
│   │   │   │       └── admin-routing.module.ts
│   │   │   ├── shared/
│   │   │   │   ├── components/
│   │   │   │   │   ├── header/
│   │   │   │   │   ├── footer/
│   │   │   │   │   ├── loading/
│   │   │   │   │   └── notification/
│   │   │   │   ├── directives/
│   │   │   │   ├── pipes/
│   │   │   │   └── shared.module.ts
│   │   │   └── layout/
│   │   │       ├── main-layout/
│   │   │       └── auth-layout/
│   │   ├── assets/
│   │   │   ├── images/
│   │   │   ├── icons/
│   │   │   └── styles/
│   │   ├── environments/
│   │   │   ├── environment.ts
│   │   │   └── environment.prod.ts
│   │   ├── styles/
│   │   │   ├── styles.scss
│   │   │   ├── _variables.scss
│   │   │   └── _mixins.scss
│   │   ├── index.html
│   │   └── main.ts
│   ├── node_modules/
│   ├── angular.json
│   ├── package.json
│   ├── tsconfig.json
│   ├── .gitignore
│   └── README.md
│
├── docs/                             # Documentation
│   ├── API.md
│   ├── DEPLOYMENT.md
│   └── USER_GUIDE.md
│
└── README.md                         # Main project documentation
```

## Key Features by Module

### Backend Modules:
- **Authentication**: JWT-based auth with Spring Security
- **User Management**: CRUD operations for users and roles
- **Application Management**: Handle membership applications
- **File Upload**: Document management for applications
- **Email Notifications**: Automated status notifications
- **Database**: SQL Server with JPA/Hibernate

### Frontend Modules:
- **Authentication**: Login/Register with JWT handling
- **Applicant Portal**: Submit and track applications
- **Admin Portal**: Review and manage applications
- **User Management**: Admin user CRUD operations
- **Responsive Design**: Bootstrap-based UI
- **Route Protection**: Guards for role-based access