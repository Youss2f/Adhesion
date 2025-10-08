import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DemandeAdhesion, ApplicationRequest } from '../models/application.model';
import { environment } from '../../../environments/environment';

const API_URL = environment.apiUrl + '/applications/';
const ADMIN_API_URL = environment.apiUrl + '/admin/applications/';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private http: HttpClient) { }

  // Applicant methods
  submitApplication(applicationData: ApplicationRequest): Observable<any> {
    return this.http.post(API_URL, applicationData);
  }

  getUserApplications(): Observable<DemandeAdhesion[]> {
    return this.http.get<DemandeAdhesion[]>(API_URL);
  }

  getApplicationById(id: number): Observable<DemandeAdhesion> {
    return this.http.get<DemandeAdhesion>(`${API_URL}${id}`);
  }

  // Admin methods
  getAllApplications(): Observable<DemandeAdhesion[]> {
    return this.http.get<DemandeAdhesion[]>(ADMIN_API_URL);
  }

  getApplicationByIdAdmin(id: number): Observable<DemandeAdhesion> {
    return this.http.get<DemandeAdhesion>(`${ADMIN_API_URL}${id}`);
  }

  validateApplication(id: number, comment?: string): Observable<any> {
    const body = comment ? { comment } : {};
    return this.http.put(`${ADMIN_API_URL}${id}/validate`, body);
  }

  rejectApplication(id: number, comment?: string): Observable<any> {
    const body = comment ? { comment } : {};
    return this.http.put(`${ADMIN_API_URL}${id}/reject`, body);
  }

  // Utility methods
  getStatusDisplayName(status: string): string {
    switch (status) {
      case 'EN_ATTENTE':
        return 'En attente';
      case 'VALIDEE':
        return 'Validée';
      case 'REJETEE':
        return 'Rejetée';
      default:
        return status;
    }
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'EN_ATTENTE':
        return 'badge bg-warning';
      case 'VALIDEE':
        return 'badge bg-success';
      case 'REJETEE':
        return 'badge bg-danger';
      default:
        return 'badge bg-secondary';
    }
  }
}