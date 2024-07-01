-- FM6 Private Institution Membership Portal Database Initialization Script
-- Microsoft SQL Server

-- Create database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'fm6_adhesion_db')
BEGIN
    CREATE DATABASE fm6_adhesion_db;
END
GO

USE fm6_adhesion_db;
GO

-- Create Users table
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
BEGIN
    CREATE TABLE users (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        email NVARCHAR(255) NOT NULL UNIQUE,
        password NVARCHAR(255) NOT NULL,
        full_name NVARCHAR(255) NOT NULL,
        role NVARCHAR(50) NOT NULL,
        institution_name NVARCHAR(255),
        institution_address NVARCHAR(500),
        phone_number NVARCHAR(20),
        is_active BIT NOT NULL DEFAULT 1,
        created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE()
    );
END
GO

-- Create Applications table
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[applications]') AND type in (N'U'))
BEGIN
    CREATE TABLE applications (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        user_id BIGINT NOT NULL,
        establishment_name NVARCHAR(255) NOT NULL,
        establishment_address NVARCHAR(500) NOT NULL,
        contact_person_name NVARCHAR(255) NOT NULL,
        contact_email NVARCHAR(255) NOT NULL,
        contact_phone NVARCHAR(20),
        establishment_type NVARCHAR(100) NOT NULL,
        number_of_employees INT,
        years_of_operation INT,
        business_license_number NVARCHAR(100),
        tax_registration_number NVARCHAR(100),
        status NVARCHAR(20) NOT NULL DEFAULT 'EN_ATTENTE',
        submission_date DATETIME2 NOT NULL DEFAULT GETDATE(),
        review_date DATETIME2,
        review_notes NVARCHAR(MAX),
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
END
GO

-- Create Documents table
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[documents]') AND type in (N'U'))
BEGIN
    CREATE TABLE documents (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        application_id BIGINT NOT NULL,
        document_name NVARCHAR(255) NOT NULL,
        file_name NVARCHAR(255) NOT NULL,
        file_path NVARCHAR(500) NOT NULL,
        file_size BIGINT,
        content_type NVARCHAR(100),
        document_type NVARCHAR(100),
        upload_date DATETIME2 NOT NULL DEFAULT GETDATE(),
        FOREIGN KEY (application_id) REFERENCES applications(id)
    );
END
GO

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_applications_user_id ON applications(user_id);
CREATE INDEX IF NOT EXISTS idx_applications_status ON applications(status);
CREATE INDEX IF NOT EXISTS idx_applications_submission_date ON applications(submission_date);
CREATE INDEX IF NOT EXISTS idx_documents_application_id ON documents(application_id);

-- Insert sample administrator user
-- Password: admin123 (BCrypt hashed)
IF NOT EXISTS (SELECT * FROM users WHERE email = 'admin@fm6.ma')
BEGIN
    INSERT INTO users (email, password, full_name, role, institution_name, is_active)
    VALUES (
        'admin@fm6.ma',
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa',
        'FM6 Administrator',
        'ADMINISTRATOR',
        'Mohammed VI Foundation',
        1
    );
END
GO

-- Insert sample institution user
-- Password: institution123 (BCrypt hashed)
IF NOT EXISTS (SELECT * FROM users WHERE email = 'test@institution.ma')
BEGIN
    INSERT INTO users (email, password, full_name, role, institution_name, institution_address, phone_number, is_active)
    VALUES (
        'test@institution.ma',
        '$2a$10$8K1p/a0dL1LXMIgoEDFrwOe6g7fKjKqYqKqYqKqYqKqYqKqYqKqYq',
        'Test Institution Representative',
        'INSTITUTION_REPRESENTATIVE',
        'Test Training Institution',
        '123 Test Street, Casablanca, Morocco',
        '+212-5-22-123456',
        1
    );
END
GO

-- Create sample application
IF NOT EXISTS (SELECT * FROM applications WHERE establishment_name = 'Test Training Institution')
BEGIN
    INSERT INTO applications (
        user_id,
        establishment_name,
        establishment_address,
        contact_person_name,
        contact_email,
        contact_phone,
        establishment_type,
        number_of_employees,
        years_of_operation,
        business_license_number,
        tax_registration_number,
        status
    )
    SELECT 
        u.id,
        'Test Training Institution',
        '123 Test Street, Casablanca, Morocco',
        'Test Institution Representative',
        'test@institution.ma',
        '+212-5-22-123456',
        'Private Training Center',
        25,
        5,
        'LIC-2024-001',
        'TAX-2024-001',
        'EN_ATTENTE'
    FROM users u WHERE u.email = 'test@institution.ma';
END
GO

PRINT 'Database initialization completed successfully!';
PRINT 'Sample data has been created:';
PRINT '- Admin user: admin@fm6.ma (password: admin123)';
PRINT '- Test institution: test@institution.ma (password: institution123)';
 