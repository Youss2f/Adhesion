package ma.fm6.membership.enums;

public enum Role {
    APPLICANT("Applicant"),
    ADMIN("Administrator");
    
    private final String displayName;
    
    Role(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}