package ma.fm6.membership.repository;

import ma.fm6.membership.entity.User;
import ma.fm6.membership.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);
    
    List<User> findByActifTrue();
    
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.actif = true")
    List<User> findActiveUsersByRole(Role role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Long countByRole(Role role);
}