---
name: Pull Request
about: Template for submitting pull requests
title: ''
labels: ''
assignees: ''
---

## 📋 Description

Brief description of what this PR accomplishes and why it's needed.

Closes #[ISSUE_NUMBER]

## 🔄 Type of Change

- [ ] 🐛 Bug fix (non-breaking change which fixes an issue)
- [ ] ✨ New feature (non-breaking change which adds functionality)
- [ ] 💥 Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] 📚 Documentation update
- [ ] 🎨 Style/formatting changes
- [ ] ♻️ Code refactoring
- [ ] ⚡ Performance improvements
- [ ] 🔧 Infrastructure/configuration changes

## 🚀 Changes Made

### Backend Changes
- [ ] Modified authentication system
- [ ] Updated entity models
- [ ] Added new API endpoints
- [ ] Enhanced security configurations
- [ ] Database schema changes

### Frontend Changes
- [ ] Updated components
- [ ] Added new features
- [ ] Modified routing
- [ ] Enhanced UI/UX
- [ ] Added form validations

### Infrastructure Changes
- [ ] Updated CI/CD pipeline
- [ ] Modified Docker configurations
- [ ] Updated documentation
- [ ] Environment configurations

## 🧪 How to Test

### Prerequisites
```bash
# Backend setup
cd backend
mvn clean install
mvn spring-boot:run

# Frontend setup
cd frontend
npm install
ng serve
```

### Testing Steps
1. [ ] Navigate to `http://localhost:4200`
2. [ ] Test user registration: Create a new account
3. [ ] Test user login: Login with created credentials
4. [ ] Test application submission: Fill and submit membership form
5. [ ] Test admin dashboard: Login as admin and review applications
6. [ ] Test JWT token functionality: Verify protected routes work correctly

### API Testing
```bash
# Test authentication endpoint
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password"}'

# Test protected endpoint
curl -X GET http://localhost:8080/api/applications \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 📱 Screenshots/GIFs

### Before
![Before](https://via.placeholder.com/500x300?text=Before+Screenshot)

### After
![After](https://via.placeholder.com/500x300?text=After+Screenshot)

## ✅ Checklist

### Code Quality
- [ ] Code follows the style guidelines
- [ ] Self-review of code performed
- [ ] Code is properly commented
- [ ] No console.log statements left in production code
- [ ] Removed any debugging/test code

### Testing
- [ ] Unit tests pass locally
- [ ] Integration tests pass locally
- [ ] Manual testing completed
- [ ] Edge cases considered and tested
- [ ] Error handling tested

### Documentation
- [ ] Updated README if needed
- [ ] Updated API documentation
- [ ] Added inline code comments where necessary
- [ ] Updated configuration guides

### Security
- [ ] No sensitive information exposed
- [ ] Authentication/authorization working correctly
- [ ] Input validation implemented
- [ ] SQL injection protection verified
- [ ] XSS protection implemented

## 🔗 Related Issues/PRs

- Fixes #[ISSUE_NUMBER]
- Related to #[ISSUE_NUMBER]
- Depends on #[PR_NUMBER]

## 📝 Additional Notes

Add any additional context, concerns, or notes for reviewers here.

### Breaking Changes
If this introduces breaking changes, list them here:

### Migration Guide
If users need to perform any migration steps:

### Performance Impact
Describe any performance implications:

---

**Reviewer Guidelines:**
- [ ] Check code quality and style
- [ ] Verify functionality works as described
- [ ] Test edge cases and error scenarios
- [ ] Validate security considerations
- [ ] Confirm documentation is updated