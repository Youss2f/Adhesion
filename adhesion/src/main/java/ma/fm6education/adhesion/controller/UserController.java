package ma.fm6education.adhesion.controller;

import ma.fm6education.adhesion.entity.User;
import ma.fm6education.adhesion.entity.UserRole;
import ma.fm6education.adhesion.service.JwtService;
import ma.fm6education.adhesion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                List<User> users = userService.findAllUsers();
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/admin/institution-representatives")
    public ResponseEntity<?> getInstitutionRepresentatives(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                List<User> users = userService.findUsersByRole(UserRole.INSTITUTION_REPRESENTATIVE);
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/admin/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                userService.deactivateUser(id);
                
                Map<String, String> response = new HashMap<>();
                response.put("message", "User deactivated successfully");
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/admin/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                userService.activateUser(id);
                
                Map<String, String> response = new HashMap<>();
                response.put("message", "User activated successfully");
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/admin/search")
    public ResponseEntity<?> searchUsersByInstitution(@RequestParam String institutionName, 
                                                    @RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                List<User> users = userService.searchByInstitutionName(institutionName);
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 