package ma.fm6education.adhesion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ma.fm6education.adhesion.entity.ApplicationStatus;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicationDto {
    
    private Long id;
    
    @NotBlank(message = "Establishment name is required")
    private String establishmentName;
    
    @NotBlank(message = "Establishment address is required")
    private String establishmentAddress;
    
    @NotBlank(message = "Contact person name is required")
    private String contactPersonName;
    
    @NotBlank(message = "Contact email is required")
    @Email(message = "Contact email should be valid")
    private String contactEmail;
    
    private String contactPhone;
    
    @NotBlank(message = "Establishment type is required")
    private String establishmentType;
    
    private Integer numberOfEmployees;
    
    private Integer yearsOfOperation;
    
    private String businessLicenseNumber;
    
    private String taxRegistrationNumber;
    
    private ApplicationStatus status;
    
    private LocalDateTime submissionDate;
    
    private LocalDateTime reviewDate;
    
    private String reviewNotes;
    
    private Long userId;
    
    private String userEmail;
    
    private List<DocumentDto> documents;
    
    // Constructors
    public ApplicationDto() {}
    
    public ApplicationDto(String establishmentName, String establishmentAddress, 
                         String contactPersonName, String contactEmail) {
        this.establishmentName = establishmentName;
        this.establishmentAddress = establishmentAddress;
        this.contactPersonName = contactPersonName;
        this.contactEmail = contactEmail;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEstablishmentName() {
        return establishmentName;
    }
    
    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }
    
    public String getEstablishmentAddress() {
        return establishmentAddress;
    }
    
    public void setEstablishmentAddress(String establishmentAddress) {
        this.establishmentAddress = establishmentAddress;
    }
    
    public String getContactPersonName() {
        return contactPersonName;
    }
    
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    public String getEstablishmentType() {
        return establishmentType;
    }
    
    public void setEstablishmentType(String establishmentType) {
        this.establishmentType = establishmentType;
    }
    
    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }
    
    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
    
    public Integer getYearsOfOperation() {
        return yearsOfOperation;
    }
    
    public void setYearsOfOperation(Integer yearsOfOperation) {
        this.yearsOfOperation = yearsOfOperation;
    }
    
    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }
    
    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }
    
    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }
    
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }
    
    public ApplicationStatus getStatus() {
        return status;
    }
    
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }
    
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
    
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }
    
    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    public String getReviewNotes() {
        return reviewNotes;
    }
    
    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public List<DocumentDto> getDocuments() {
        return documents;
    }
    
    public void setDocuments(List<DocumentDto> documents) {
        this.documents = documents;
    }
} 