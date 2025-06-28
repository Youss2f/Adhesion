package ma.fm6education.adhesion.service;

import ma.fm6education.adhesion.dto.UserRegistrationDto;
import ma.fm6education.adhesion.entity.User;
import ma.fm6education.adhesion.entity.UserRole;
import ma.fm6education.adhesion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(UserRegistrationDto registrationDto) {
        // Check if user already exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        
        // Create new user
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setFullName(registrationDto.getFullName());
        user.setInstitutionName(registrationDto.getInstitutionName());
        user.setInstitutionAddress(registrationDto.getInstitutionAddress());
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user.setRole(UserRole.INSTITUTION_REPRESENTATIVE);
        user.setIsActive(true);
        
        return userRepository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> findUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }
    
    public List<User> findActiveUsers() {
        return userRepository.findByIsActive(true);
    }
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    public void deactivateUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setIsActive(false);
            userRepository.save(user);
        }
    }
    
    public void activateUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setIsActive(true);
            userRepository.save(user);
        }
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public List<User> searchByInstitutionName(String institutionName) {
        return userRepository.findByInstitutionNameContaining(institutionName);
    }
}
