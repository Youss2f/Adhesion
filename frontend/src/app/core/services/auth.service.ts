import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { AuthResponse, LoginRequest, RegisterRequest, User } from '../models/auth.model';
import { StorageService } from './storage.service';
import { environment } from '../../../environments/environment';

const AUTH_API = environment.apiUrl + '/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {
    // Check if user is already logged in
    const token = this.storageService.getToken();
    const user = this.storageService.getUser();
    if (token && user) {
      this.currentUserSubject.next(user);
    }
  }

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

  register(signUpInfo: RegisterRequest): Observable<any> {
    return this.http.post(AUTH_API + 'register', signUpInfo, httpOptions);
  }

  logout(): void {
    this.storageService.clean();
    this.currentUserSubject.next(null);
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  public isLoggedIn(): boolean {
    return !!this.storageService.getToken();
  }

  public isAdmin(): boolean {
    const user = this.currentUserValue;
    return user?.role === 'ADMIN';
  }

  public isApplicant(): boolean {
    const user = this.currentUserValue;
    return user?.role === 'APPLICANT';
  }

  public getToken(): string | null {
    return this.storageService.getToken();
  }

  public refreshCurrentUser(): void {
    const user = this.storageService.getUser();
    this.currentUserSubject.next(user);
  }
}