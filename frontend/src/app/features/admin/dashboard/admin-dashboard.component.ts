import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../../../core/services/application.service';
import { DemandeAdhesion } from '../../../core/models/application.model';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  applications: DemandeAdhesion[] = [];
  pendingApplications: DemandeAdhesion[] = [];
  selectedApplication: DemandeAdhesion | null = null;
  isLoading = true;
  error = '';
  
  // Statistics
  totalApplications = 0;
  pendingCount = 0;
  approvedCount = 0;
  rejectedCount = 0;

  // Pagination
  currentPage = 1;
  itemsPerPage = 10;
  totalPages = 1;

  // Filters
  statusFilter = 'ALL';
  searchTerm = '';

  constructor(
    private applicationService: ApplicationService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    this.isLoading = true;
    this.error = '';
    
    this.applicationService.getAllApplications().subscribe({
      next: (applications) => {
        this.applications = applications;
        this.calculateStatistics();
        this.filterApplications();
        this.isLoading = false;
      },
      error: (error) => {
        this.error = 'Erreur lors du chargement des demandes';
        this.isLoading = false;
        console.error('Error loading applications:', error);
      }
    });
  }

  calculateStatistics(): void {
    this.totalApplications = this.applications.length;
    this.pendingCount = this.applications.filter(app => app.statut === 'EN_ATTENTE').length;
    this.approvedCount = this.applications.filter(app => app.statut === 'VALIDEE').length;
    this.rejectedCount = this.applications.filter(app => app.statut === 'REJETEE').length;
  }

  filterApplications(): void {
    let filtered = [...this.applications];

    // Status filter
    if (this.statusFilter !== 'ALL') {
      filtered = filtered.filter(app => app.statut === this.statusFilter);
    }

    // Search filter
    if (this.searchTerm) {
      const search = this.searchTerm.toLowerCase();
      filtered = filtered.filter(app => 
        app.etablissement?.nom.toLowerCase().includes(search) ||
        app.user?.nom.toLowerCase().includes(search) ||
        app.user?.prenom.toLowerCase().includes(search) ||
        app.details.toLowerCase().includes(search)
      );
    }

    // Pagination
    this.totalPages = Math.ceil(filtered.length / this.itemsPerPage);
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.pendingApplications = filtered.slice(startIndex, startIndex + this.itemsPerPage);
  }

  onStatusFilterChange(): void {
    this.currentPage = 1;
    this.filterApplications();
  }

  onSearchChange(): void {
    this.currentPage = 1;
    this.filterApplications();
  }

  viewApplication(application: DemandeAdhesion): void {
    this.selectedApplication = application;
  }

  approveApplication(applicationId: number, comment?: string): void {
    this.applicationService.validateApplication(applicationId, comment).subscribe({
      next: () => {
        this.loadApplications(); // Reload to get updated data
        this.selectedApplication = null;
      },
      error: (error) => {
        console.error('Error approving application:', error);
        this.error = 'Erreur lors de la validation de la demande';
      }
    });
  }

  rejectApplication(applicationId: number, comment?: string): void {
    this.applicationService.rejectApplication(applicationId, comment).subscribe({
      next: () => {
        this.loadApplications(); // Reload to get updated data
        this.selectedApplication = null;
      },
      error: (error) => {
        console.error('Error rejecting application:', error);
        this.error = 'Erreur lors du rejet de la demande';
      }
    });
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.filterApplications();
    }
  }

  getStatusDisplayName(status: string): string {
    return this.applicationService.getStatusDisplayName(status);
  }

  getStatusClass(status: string): string {
    return this.applicationService.getStatusClass(status);
  }

  formatDate(date: Date | string): string {
    if (!date) return 'N/A';
    return new Date(date).toLocaleDateString('fr-FR');
  }

  closeModal(): void {
    this.selectedApplication = null;
  }
}