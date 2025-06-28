package ma.fm6education.adhesion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "applications")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotBlank(message = "Establishment name is required")
    @Column(name = "establishment_name", nullable = false)
    private String establishmentName;
    
    @NotBlank(message = "Establishment address is required")
    @Column(name = "establishment_address", nullable = false)
    private String establishmentAddress;
    
    @NotBlank(message = "Contact person name is required")
    @Column(name = "contact_person_name", nullable = false)
    private String contactPersonName;
    
    @NotBlank(message = "Contact email is required")
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @NotBlank(message = "Establishment type is required")
    @Column(name = "establishment_type", nullable = false)
    private String establishmentType;
    
    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;
    
    @Column(name = "years_of_operation")
    private Integer yearsOfOperation;
    
    @Column(name = "business_license_number")
    private String businessLicenseNumber;
    
    @Column(name = "tax_registration_number")
    private String taxRegistrationNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.EN_ATTENTE;
    
    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;
    
    @Column(name = "review_date")
    private LocalDateTime reviewDate;
    
    @Column(name = "review_notes", columnDefinition = "TEXT")
    private String reviewNotes;
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents;
    
    @PrePersist
    protected void onCreate() {
        submissionDate = LocalDateTime.now();
    }
    
    // Constructors
    public Application() {}
    
    public Application(User user, String establishmentName, String establishmentAddress, 
                      String contactPersonName, String contactEmail) {
        this.user = user;
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
    
    public List<Document> getDocuments() {
        return documents;
    }
    
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
} 