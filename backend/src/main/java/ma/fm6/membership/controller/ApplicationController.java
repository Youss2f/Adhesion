package ma.fm6.membership.controller;

import jakarta.validation.Valid;
import ma.fm6.membership.dto.request.ApplicationRequest;
import ma.fm6.membership.dto.response.MessageResponse;
import ma.fm6.membership.entity.DemandeAdhesion;
import ma.fm6.membership.entity.User;
import ma.fm6.membership.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @PostMapping
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationRequest request,
                                             @AuthenticationPrincipal User currentUser) {
        try {
            DemandeAdhesion demande = applicationService.submitApplication(request, currentUser);
            return ResponseEntity.ok(new MessageResponse("Demande soumise avec succès. ID: " + demande.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Erreur lors de la soumission: " + e.getMessage()));
        }
    }
    
    @GetMapping
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<List<DemandeAdhesion>> getUserApplications(@AuthenticationPrincipal User currentUser) {
        List<DemandeAdhesion> demandes = applicationService.getUserApplications(currentUser.getId());
        return ResponseEntity.ok(demandes);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<?> getApplication(@PathVariable Long id,
                                          @AuthenticationPrincipal User currentUser) {
        try {
            DemandeAdhesion demande = applicationService.getApplicationById(id, currentUser.getId());
            return ResponseEntity.ok(demande);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Erreur: " + e.getMessage()));
        }
    }
}