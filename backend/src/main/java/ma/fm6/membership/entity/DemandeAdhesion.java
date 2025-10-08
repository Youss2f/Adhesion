package ma.fm6.membership.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ma.fm6.membership.enums.StatutDemande;

import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_adhesion")
public class DemandeAdhesion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "date_soumission")
    private LocalDateTime dateSoumission;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutDemande statut;
    
    @NotBlank
    @Size(max = 1000)
    @Column(name = "details")
    private String details;
    
    @Column(name = "commentaire_admin")
    private String commentaireAdmin;
    
    @Column(name = "date_traitement")
    private LocalDateTime dateTraitement;
    
    @Column(name = "documents_path")
    private String documentsPath;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User adminTraitant;
    
    // Constructors
    public DemandeAdhesion() {
        this.dateSoumission = LocalDateTime.now();
        this.statut = StatutDemande.EN_ATTENTE;
    }
    
    public DemandeAdhesion(String details, Etablissement etablissement, User user) {
        this();
        this.details = details;
        this.etablissement = etablissement;
        this.user = user;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }
    
    public void setDateSoumission(LocalDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }
    
    public StatutDemande getStatut() {
        return statut;
    }
    
    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public String getCommentaireAdmin() {
        return commentaireAdmin;
    }
    
    public void setCommentaireAdmin(String commentaireAdmin) {
        this.commentaireAdmin = commentaireAdmin;
    }
    
    public LocalDateTime getDateTraitement() {
        return dateTraitement;
    }
    
    public void setDateTraitement(LocalDateTime dateTraitement) {
        this.dateTraitement = dateTraitement;
    }
    
    public String getDocumentsPath() {
        return documentsPath;
    }
    
    public void setDocumentsPath(String documentsPath) {
        this.documentsPath = documentsPath;
    }
    
    public Etablissement getEtablissement() {
        return etablissement;
    }
    
    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getAdminTraitant() {
        return adminTraitant;
    }
    
    public void setAdminTraitant(User adminTraitant) {
        this.adminTraitant = adminTraitant;
    }
}