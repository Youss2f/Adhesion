// src/main/java/ma/fm6education/adhesion/repository/UserRepository.java
package ma.fm6education.adhesion.repository;

import ma.fm6education.adhesion.entity.User;
import ma.fm6education.adhesion.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    List<User> findByRole(UserRole role);
    
    List<User> findByIsActive(Boolean isActive);
    
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.isActive = true")
    List<User> findActiveUsersByRole(@Param("role") UserRole role);
    
    @Query("SELECT u FROM User u WHERE u.institutionName LIKE %:institutionName%")
    List<User> findByInstitutionNameContaining(@Param("institutionName") String institutionName);
}
