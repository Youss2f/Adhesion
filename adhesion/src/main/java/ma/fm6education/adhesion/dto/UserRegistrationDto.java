package ma.fm6education.adhesion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotBlank(message = "Institution name is required")
    private String institutionName;
    
    @NotBlank(message = "Institution address is required")
    private String institutionAddress;
    
    private String phoneNumber;
    
    // Constructors
    public UserRegistrationDto() {}
    
    public UserRegistrationDto(String email, String password, String fullName, 
                              String institutionName, String institutionAddress) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.institutionName = institutionName;
        this.institutionAddress = institutionAddress;
    }
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getInstitutionName() {
        return institutionName;
    }
    
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    
    public String getInstitutionAddress() {
        return institutionAddress;
    }
    
    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
} 