package ma.fm6education.adhesion.service;

import ma.fm6education.adhesion.dto.ApplicationDto;
import ma.fm6education.adhesion.entity.Application;
import ma.fm6education.adhesion.entity.ApplicationStatus;
import ma.fm6education.adhesion.entity.User;
import ma.fm6education.adhesion.repository.ApplicationRepository;
import ma.fm6education.adhesion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Application submitApplication(ApplicationDto applicationDto, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOpt.get();
        
        Application application = new Application();
        application.setUser(user);
        application.setEstablishmentName(applicationDto.getEstablishmentName());
        application.setEstablishmentAddress(applicationDto.getEstablishmentAddress());
        application.setContactPersonName(applicationDto.getContactPersonName());
        application.setContactEmail(applicationDto.getContactEmail());
        application.setContactPhone(applicationDto.getContactPhone());
        application.setEstablishmentType(applicationDto.getEstablishmentType());
        application.setNumberOfEmployees(applicationDto.getNumberOfEmployees());
        application.setYearsOfOperation(applicationDto.getYearsOfOperation());
        application.setBusinessLicenseNumber(applicationDto.getBusinessLicenseNumber());
        application.setTaxRegistrationNumber(applicationDto.getTaxRegistrationNumber());
        application.setStatus(ApplicationStatus.EN_ATTENTE);
        application.setSubmissionDate(LocalDateTime.now());
        
        return applicationRepository.save(application);
    }
    
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }
    
    public List<Application> findByUserId(Long userId) {
        return applicationRepository.findByUserIdOrderBySubmissionDateDesc(userId);
    }
    
    public List<Application> findByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatusOrderBySubmissionDateAsc(status);
    }
    
    public Page<Application> findByStatus(ApplicationStatus status, Pageable pageable) {
        return applicationRepository.findByStatus(status, pageable);
    }
    
    public List<Application> findAllApplications() {
        return applicationRepository.findAll();
    }
    
    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status, String reviewNotes) {
        Optional<Application> applicationOpt = applicationRepository.findById(applicationId);
        if (applicationOpt.isEmpty()) {
            throw new RuntimeException("Application not found");
        }
        
        Application application = applicationOpt.get();
        application.setStatus(status);
        application.setReviewDate(LocalDateTime.now());
        application.setReviewNotes(reviewNotes);
        
        return applicationRepository.save(application);
    }
    
    public Application approveApplication(Long applicationId, String reviewNotes) {
        return updateApplicationStatus(applicationId, ApplicationStatus.VALIDEE, reviewNotes);
    }
    
    public Application rejectApplication(Long applicationId, String reviewNotes) {
        return updateApplicationStatus(applicationId, ApplicationStatus.REJETEE, reviewNotes);
    }
    
    public List<Application> searchByEstablishmentName(String establishmentName) {
        return applicationRepository.findByEstablishmentNameContaining(establishmentName);
    }
    
    public List<Application> findBySubmissionDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return applicationRepository.findBySubmissionDateBetween(startDate, endDate);
    }
    
    public Long countByStatus(ApplicationStatus status) {
        return applicationRepository.countByStatus(status);
    }
    
    public ApplicationDto convertToDto(Application application) {
        ApplicationDto dto = new ApplicationDto();
        dto.setId(application.getId());
        dto.setEstablishmentName(application.getEstablishmentName());
        dto.setEstablishmentAddress(application.getEstablishmentAddress());
        dto.setContactPersonName(application.getContactPersonName());
        dto.setContactEmail(application.getContactEmail());
        dto.setContactPhone(application.getContactPhone());
        dto.setEstablishmentType(application.getEstablishmentType());
        dto.setNumberOfEmployees(application.getNumberOfEmployees());
        dto.setYearsOfOperation(application.getYearsOfOperation());
        dto.setBusinessLicenseNumber(application.getBusinessLicenseNumber());
        dto.setTaxRegistrationNumber(application.getTaxRegistrationNumber());
        dto.setStatus(application.getStatus());
        dto.setSubmissionDate(application.getSubmissionDate());
        dto.setReviewDate(application.getReviewDate());
        dto.setReviewNotes(application.getReviewNotes());
        dto.setUserId(application.getUser().getId());
        dto.setUserEmail(application.getUser().getEmail());
        
        return dto;
    }
} 