package ma.fm6.membership.service;

import ma.fm6.membership.dto.request.ApplicationRequest;
import ma.fm6.membership.entity.DemandeAdhesion;
import ma.fm6.membership.entity.User;
import ma.fm6.membership.enums.StatutDemande;
import ma.fm6.membership.repository.DemandeAdhesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {
    
    @Autowired
    private DemandeAdhesionRepository demandeRepository;
    
    @Autowired
    private EmailService emailService;
    
    public DemandeAdhesion submitApplication(ApplicationRequest request, User user) {
        DemandeAdhesion demande = new DemandeAdhesion();
        demande.setDetails(request.getDetails());
        demande.setDocumentsPath(request.getDocumentsPath());
        demande.setUser(user);
        demande.setEtablissement(user.getEtablissement());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setDateSoumission(LocalDateTime.now());
        
        demande = demandeRepository.save(demande);
        
        // Send confirmation email
        emailService.sendApplicationConfirmation(user.getEmail(), demande.getId());
        
        return demande;
    }
    
    public List<DemandeAdhesion> getUserApplications(Long userId) {
        return demandeRepository.findByUserIdOrderByDateSoumissionDesc(userId);
    }
    
    public DemandeAdhesion getApplicationById(Long id, Long userId) {
        DemandeAdhesion demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        
        if (!demande.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès non autorisé à cette demande");
        }
        
        return demande;
    }
    
    public List<DemandeAdhesion> getAllPendingApplications() {
        return demandeRepository.findByStatutOrderByDateSoumissionAsc(StatutDemande.EN_ATTENTE);
    }
    
    public List<DemandeAdhesion> getAllApplications() {
        return demandeRepository.findAll();
    }
    
    public DemandeAdhesion getApplicationById(Long id) {
        return demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
    }
    
    public DemandeAdhesion validateApplication(Long id, User admin, String comment) {
        DemandeAdhesion demande = getApplicationById(id);
        demande.setStatut(StatutDemande.VALIDEE);
        demande.setCommentaireAdmin(comment);
        demande.setDateTraitement(LocalDateTime.now());
        demande.setAdminTraitant(admin);
        
        demande = demandeRepository.save(demande);
        
        // Send validation email
        emailService.sendApplicationValidation(demande.getUser().getEmail(), demande.getId());
        
        return demande;
    }
    
    public DemandeAdhesion rejectApplication(Long id, User admin, String comment) {
        DemandeAdhesion demande = getApplicationById(id);
        demande.setStatut(StatutDemande.REJETEE);
        demande.setCommentaireAdmin(comment);
        demande.setDateTraitement(LocalDateTime.now());
        demande.setAdminTraitant(admin);
        
        demande = demandeRepository.save(demande);
        
        // Send rejection email
        emailService.sendApplicationRejection(demande.getUser().getEmail(), demande.getId(), comment);
        
        return demande;
    }
}