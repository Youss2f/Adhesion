package ma.fm6.membership.enums;

public enum StatutDemande {
    EN_ATTENTE("En attente"),
    VALIDEE("Validée"),
    REJETEE("Rejetée");
    
    private final String displayName;
    
    StatutDemande(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}