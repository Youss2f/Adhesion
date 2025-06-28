// path: src/main/java/ma/fm6education/adhesion/entity/Etablissement.java
package ma.fm6education.adhesion.entity;

import jakarta.persistence.*;

@Entity
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String autorisationExercice;
    private String statut;

    // Getters et setters
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

    public String getAutorisationExercice() {
        return autorisationExercice;
    }

    public void setAutorisationExercice(String autorisationExercice) {
        this.autorisationExercice = autorisationExercice;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
