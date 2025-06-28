package ma.fm6education.adhesion.controller;

import ma.fm6education.adhesion.dto.ApplicationDto;
import ma.fm6education.adhesion.entity.Application;
import ma.fm6education.adhesion.entity.ApplicationStatus;
import ma.fm6education.adhesion.entity.UserRole;
import ma.fm6education.adhesion.service.ApplicationService;
import ma.fm6education.adhesion.service.JwtService;
import ma.fm6education.adhesion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping
    public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationDto applicationDto,
                                             @RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                Long userId = jwtService.extractUserId(token);
                
                Application application = applicationService.submitApplication(applicationDto, userId);
                ApplicationDto responseDto = applicationService.convertToDto(application);
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Application submitted successfully");
                response.put("application", responseDto);
                
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
    
    @GetMapping("/my-applications")
    public ResponseEntity<?> getMyApplications(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                Long userId = jwtService.extractUserId(token);
                
                List<Application> applications = applicationService.findByUserId(userId);
                List<ApplicationDto> applicationDtos = applications.stream()
                        .map(applicationService::convertToDto)
                        .collect(Collectors.toList());
                
                return ResponseEntity.ok(applicationDtos);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable Long id,
                                              @RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                Long userId = jwtService.extractUserId(token);
                
                // Check if user has access to this application
                if (role.equals(UserRole.INSTITUTION_REPRESENTATIVE.name())) {
                    List<Application> userApplications = applicationService.findByUserId(userId);
                    boolean hasAccess = userApplications.stream()
                            .anyMatch(app -> app.getId().equals(id));
                    if (!hasAccess) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                    }
                }
                
                Application application = applicationService.findById(id).orElse(null);
                if (application == null) {
                    return ResponseEntity.notFound().build();
                }
                
                ApplicationDto applicationDto = applicationService.convertToDto(application);
                return ResponseEntity.ok(applicationDto);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllApplications(@RequestHeader("Authorization") String token,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(required = false) String status) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                Pageable pageable = PageRequest.of(page, size);
                List<Application> applications;
                
                if (status != null && !status.isEmpty()) {
                    ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status.toUpperCase());
                    applications = applicationService.findByStatus(applicationStatus);
                } else {
                    applications = applicationService.findAllApplications();
                }
                
                List<ApplicationDto> applicationDtos = applications.stream()
                        .map(applicationService::convertToDto)
                        .collect(Collectors.toList());
                
                return ResponseEntity.ok(applicationDtos);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveApplication(@PathVariable Long id,
                                              @RequestHeader("Authorization") String token,
                                              @RequestBody(required = false) Map<String, String> request) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                String reviewNotes = request != null ? request.get("reviewNotes") : null;
                Application application = applicationService.approveApplication(id, reviewNotes);
                ApplicationDto applicationDto = applicationService.convertToDto(application);
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Application approved successfully");
                response.put("application", applicationDto);
                
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
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<?> rejectApplication(@PathVariable Long id,
                                             @RequestHeader("Authorization") String token,
                                             @RequestBody(required = false) Map<String, String> request) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                String reviewNotes = request != null ? request.get("reviewNotes") : null;
                Application application = applicationService.rejectApplication(id, reviewNotes);
                ApplicationDto applicationDto = applicationService.convertToDto(application);
                
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Application rejected");
                response.put("application", applicationDto);
                
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
    
    @GetMapping("/admin/stats")
    public ResponseEntity<?> getApplicationStats(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String role = jwtService.extractRole(token);
                
                if (!role.equals(UserRole.ADMINISTRATOR.name())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Access denied"));
                }
                
                Map<String, Object> stats = new HashMap<>();
                stats.put("pending", applicationService.countByStatus(ApplicationStatus.EN_ATTENTE));
                stats.put("approved", applicationService.countByStatus(ApplicationStatus.VALIDEE));
                stats.put("rejected", applicationService.countByStatus(ApplicationStatus.REJETEE));
                stats.put("total", applicationService.countByStatus(ApplicationStatus.EN_ATTENTE) +
                        applicationService.countByStatus(ApplicationStatus.VALIDEE) +
                        applicationService.countByStatus(ApplicationStatus.REJETEE));
                
                return ResponseEntity.ok(stats);
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