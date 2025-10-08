package ma.fm6.membership.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApplicationRequest {
    
    @NotBlank
    @Size(max = 1000)
    private String details;
    
    private String documentsPath;
    
    public ApplicationRequest() {}
    
    public ApplicationRequest(String details, String documentsPath) {
        this.details = details;
        this.documentsPath = documentsPath;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public String getDocumentsPath() {
        return documentsPath;
    }
    
    public void setDocumentsPath(String documentsPath) {
        this.documentsPath = documentsPath;
    }
}