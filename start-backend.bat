@echo off
echo Starting FM6 Private Institution Membership Portal Backend...
echo.

cd adhesion

echo Building the application...
call mvn clean install -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo Build failed! Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Starting the application...
echo Backend will be available at: http://localhost:8080
echo API endpoints will be available at: http://localhost:8080/api
echo Health check: http://localhost:8080/api/health
echo.
echo Default users:
echo - Admin: admin@fm6.ma / admin123
echo - Test Institution: test@institution.ma / institution123
echo.

call mvn spring-boot:run

pause 