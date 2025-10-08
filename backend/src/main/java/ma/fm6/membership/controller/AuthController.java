package ma.fm6.membership.controller;

import jakarta.validation.Valid;
import ma.fm6.membership.dto.request.LoginRequest;
import ma.fm6.membership.dto.request.RegisterRequest;
import ma.fm6.membership.dto.response.JwtResponse;
import ma.fm6.membership.dto.response.MessageResponse;
import ma.fm6.membership.entity.Etablissement;
import ma.fm6.membership.entity.User;
import ma.fm6.membership.enums.Role;
import ma.fm6.membership.repository.UserRepository;
import ma.fm6.membership.security.JwtUtils;
import ma.fm6.membership.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getNom(),
                userDetails.getPrenom(),
                userDetails.getRole()));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email déjà utilisé!"));
        }
        
        // Create new user's account
        User user = new User(signUpRequest.getNom(),
                signUpRequest.getPrenom(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                Role.APPLICANT);
        
        user = userRepository.save(user);
        
        // Create associated Etablissement
        Etablissement etablissement = new Etablissement(
                signUpRequest.getNomEtablissement(),
                signUpRequest.getAdresse(),
                signUpRequest.getTelephone(),
                signUpRequest.getEmailEtablissement(),
                user);
        
        user.setEtablissement(etablissement);
        userRepository.save(user);
        
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
    }
}