package ma.fm6.membership.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "etablissements")
public class Etablissement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "nom")
    private String nom;
    
    @NotBlank
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    
    @Size(max = 20)
    @Column(name = "telephone")
    private String telephone;
    
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandeAdhesion> demandes;
    
    // Constructors
    public Etablissement() {
        this.dateCreation = LocalDateTime.now();
    }
    
    public Etablissement(String nom, String adresse, String telephone, String email, User user) {
        this();
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<DemandeAdhesion> getDemandes() {
        return demandes;
    }
    
    public void setDemandes(List<DemandeAdhesion> demandes) {
        this.demandes = demandes;
    }
}