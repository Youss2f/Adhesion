package ma.fm6education.adhesion.config;

import ma.fm6education.adhesion.entity.User;
import ma.fm6education.adhesion.entity.UserRole;
import ma.fm6education.adhesion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin user if it doesn't exist
        if (!userRepository.existsByEmail("admin@fm6.ma")) {
            User adminUser = new User();
            adminUser.setEmail("admin@fm6.ma");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFullName("FM6 Administrator");
            adminUser.setRole(UserRole.ADMINISTRATOR);
            adminUser.setInstitutionName("Mohammed VI Foundation");
            adminUser.setIsActive(true);
            
            userRepository.save(adminUser);
            System.out.println("Admin user created: admin@fm6.ma / admin123");
        }
        
        // Create sample institution user if it doesn't exist
        if (!userRepository.existsByEmail("test@institution.ma")) {
            User institutionUser = new User();
            institutionUser.setEmail("test@institution.ma");
            institutionUser.setPassword(passwordEncoder.encode("institution123"));
            institutionUser.setFullName("Test Institution Representative");
            institutionUser.setRole(UserRole.INSTITUTION_REPRESENTATIVE);
            institutionUser.setInstitutionName("Test Training Institution");
            institutionUser.setInstitutionAddress("123 Test Street, Casablanca, Morocco");
            institutionUser.setPhoneNumber("+212-5-22-123456");
            institutionUser.setIsActive(true);
            
            userRepository.save(institutionUser);
            System.out.println("Test institution user created: test@institution.ma / institution123");
        }
        
        System.out.println("Data initialization completed!");
    }
}
