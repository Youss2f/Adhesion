package ma.fm6.membership.repository;

import ma.fm6.membership.entity.DemandeAdhesion;
import ma.fm6.membership.enums.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DemandeAdhesionRepository extends JpaRepository<DemandeAdhesion, Long> {
    
    List<DemandeAdhesion> findByStatut(StatutDemande statut);
    
    List<DemandeAdhesion> findByUserId(Long userId);
    
    List<DemandeAdhesion> findByEtablissementId(Long etablissementId);
    
    @Query("SELECT d FROM DemandeAdhesion d WHERE d.statut = :statut ORDER BY d.dateSoumission ASC")
    List<DemandeAdhesion> findByStatutOrderByDateSoumissionAsc(StatutDemande statut);
    
    @Query("SELECT d FROM DemandeAdhesion d WHERE d.user.id = :userId ORDER BY d.dateSoumission DESC")
    List<DemandeAdhesion> findByUserIdOrderByDateSoumissionDesc(Long userId);
    
    @Query("SELECT COUNT(d) FROM DemandeAdhesion d WHERE d.statut = :statut")
    Long countByStatut(StatutDemande statut);
    
    @Query("SELECT d FROM DemandeAdhesion d WHERE d.dateSoumission BETWEEN :startDate AND :endDate")
    List<DemandeAdhesion> findByDateSoumissionBetween(LocalDateTime startDate, LocalDateTime endDate);
}