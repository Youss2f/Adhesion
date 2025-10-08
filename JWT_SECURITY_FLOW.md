# JWT Security Flow and Integration - Fondation Mohammed VI Membership Portal

## Overview
The Fondation Mohammed VI Membership Portal implements a comprehensive JWT-based authentication and authorization system that ensures secure communication between the Angular frontend and Spring Boot backend.

## 1. JWT Flow Architecture

### Authentication Process
```
1. User submits credentials (email/password)
2. Spring Boot validates credentials against database
3. If valid, JWT token is generated with user information
4. Token is sent back to Angular frontend
5. Frontend stores token in sessionStorage
6. All subsequent API calls include the JWT token in Authorization header
7. Backend validates token on each request and extracts user context
```

## 2. Backend JWT Implementation

### JWT Token Generation (`JwtUtils.java`)
```java
public String generateJwtToken(Authentication authentication) {
    User userPrincipal = (User) authentication.getPrincipal();
    
    return Jwts.builder()
            .subject((userPrincipal.getUsername()))
            .issuedAt(new Date())
            .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(getSigningKey())
            .compact();
}
```

### JWT Token Validation
```java
public boolean validateJwtToken(String authToken) {
    try {
        Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(authToken);
        return true;
    } catch (MalformedJwtException | ExpiredJwtException | 
             UnsupportedJwtException | IllegalArgumentException e) {
        logger.error("JWT validation error: {}", e.getMessage());
        return false;
    }
}
```

### Security Filter Chain (`JwtAuthTokenFilter.java`)
```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                               FilterChain filterChain) throws ServletException, IOException {
    try {
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, 
                                                           userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    } catch (Exception e) {
        logger.error("Cannot set user authentication: {}", e.getMessage());
    }
    
    filterChain.doFilter(request, response);
}
```

## 3. Role-Based Access Control

### Backend Security Configuration
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/applications/**").hasRole("APPLICANT")
                    .anyRequest().authenticated()
            );
    
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

### Method-Level Security
```java
@PostMapping
@PreAuthorize("hasRole('APPLICANT')")
public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationRequest request,
                                         @AuthenticationPrincipal User currentUser) {
    // Only APPLICANT role can submit applications
}

@GetMapping("/admin/applications")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<List<DemandeAdhesion>> getAllApplications() {
    // Only ADMIN role can view all applications
}
```

## 4. Frontend JWT Integration

### JWT Interceptor (`jwt.interceptor.ts`)
```typescript
@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private storageService: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.storageService.getToken();
    
    if (token) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}
```

### Authentication Service (`auth.service.ts`)
```typescript
login(credentials: LoginRequest): Observable<AuthResponse> {
  return this.http.post<AuthResponse>(AUTH_API + 'login', credentials, httpOptions)
    .pipe(
      tap(response => {
        this.storageService.saveToken(response.accessToken);
        const user: User = {
          id: response.id,
          nom: response.nom,
          prenom: response.prenom,
          email: response.email,
          role: response.role
        };
        this.storageService.saveUser(user);
        this.currentUserSubject.next(user);
      })
    );
}
```

### Route Guards

#### Auth Guard (`auth.guard.ts`)
```typescript
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['/auth/login']);
      return false;
    }
  }
}
```

#### Admin Guard (`admin.guard.ts`)
```typescript
@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    if (this.authService.isLoggedIn() && this.authService.isAdmin()) {
      return true;
    } else {
      this.router.navigate(['/auth/login']);
      return false;
    }
  }
}
```

## 5. Token Storage and Management

### Secure Token Storage
```typescript
// StorageService uses sessionStorage for security
public saveToken(token: string): void {
  window.sessionStorage.removeItem(TOKEN_KEY);
  window.sessionStorage.setItem(TOKEN_KEY, token);
}

public getToken(): string | null {
  return window.sessionStorage.getItem(TOKEN_KEY);
}
```

### Automatic Token Attachment
- All HTTP requests automatically include the JWT token via the `JwtInterceptor`
- No manual token management required in individual service calls
- Centralized token handling ensures consistency

## 6. Security Best Practices Implemented

### Backend Security
1. **Token Expiration**: JWT tokens expire after 24 hours (configurable)
2. **Secure Secret**: Long, complex JWT secret key
3. **HTTPS Ready**: Configuration supports HTTPS in production
4. **CORS Configuration**: Proper CORS settings for frontend communication
5. **Password Hashing**: BCrypt for secure password storage
6. **Role-Based Authorization**: Method and URL-level security

### Frontend Security
1. **Session Storage**: Tokens stored in sessionStorage (not localStorage)
2. **Automatic Logout**: Token removal on logout
3. **Route Protection**: Guards prevent unauthorized access
4. **Error Handling**: Proper handling of authentication errors
5. **Token Validation**: Client-side token existence checks

## 7. API Request Flow Example

### Successful Request Flow
```
1. Angular service makes API call
2. JwtInterceptor automatically adds "Authorization: Bearer <token>" header
3. Spring Boot receives request
4. JwtAuthTokenFilter extracts and validates token
5. If valid, user context is set in SecurityContextHolder
6. Controller method executes with authenticated user
7. Response sent back to Angular
```

### Failed Authentication Flow
```
1. Invalid/expired token detected by backend filter
2. JwtAuthEntryPoint returns 401 Unauthorized
3. Angular HTTP error interceptor can handle the error
4. User redirected to login page
5. Session storage cleared
```

## 8. Configuration Settings

### Backend JWT Configuration (`application.yml`)
```yaml
fm6:
  app:
    jwtSecret: fm6SecretKey2024ForMembershipPortalVeryLongSecretKeyToEnsureSecurity
    jwtExpirationMs: 86400000  # 24 hours
```

### Frontend Environment Configuration
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

This JWT implementation provides a robust, scalable, and secure authentication system that supports role-based access control and seamless integration between the Angular frontend and Spring Boot backend.