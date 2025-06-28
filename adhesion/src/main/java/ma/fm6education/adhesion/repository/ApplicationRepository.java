package ma.fm6education.adhesion.repository;

import ma.fm6education.adhesion.entity.Application;
import ma.fm6education.adhesion.entity.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findByUserId(Long userId);
    
    List<Application> findByStatus(ApplicationStatus status);
    
    Page<Application> findByStatus(ApplicationStatus status, Pageable pageable);
    
    @Query("SELECT a FROM Application a WHERE a.user.id = :userId ORDER BY a.submissionDate DESC")
    List<Application> findByUserIdOrderBySubmissionDateDesc(@Param("userId") Long userId);
    
    @Query("SELECT a FROM Application a WHERE a.status = :status ORDER BY a.submissionDate ASC")
    List<Application> findByStatusOrderBySubmissionDateAsc(@Param("status") ApplicationStatus status);
    
    @Query("SELECT a FROM Application a WHERE a.establishmentName LIKE %:establishmentName%")
    List<Application> findByEstablishmentNameContaining(@Param("establishmentName") String establishmentName);
    
    @Query("SELECT a FROM Application a WHERE a.submissionDate BETWEEN :startDate AND :endDate")
    List<Application> findBySubmissionDateBetween(@Param("startDate") LocalDateTime startDate, 
                                                 @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(a) FROM Application a WHERE a.status = :status")
    Long countByStatus(@Param("status") ApplicationStatus status);
} 