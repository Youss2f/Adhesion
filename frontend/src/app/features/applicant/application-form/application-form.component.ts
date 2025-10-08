import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApplicationService } from '../../../core/services/application.service';
import { ApplicationRequest } from '../../../core/models/application.model';

@Component({
  selector: 'app-application-form',
  templateUrl: './application-form.component.html',
  styleUrls: ['./application-form.component.scss']
})
export class ApplicationFormComponent implements OnInit {
  applicationForm!: FormGroup;
  isSubmitting = false;
  submitError = '';
  submitSuccess = '';

  constructor(
    private formBuilder: FormBuilder,
    private applicationService: ApplicationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    this.applicationForm = this.formBuilder.group({
      details: ['', [Validators.required, Validators.minLength(50), Validators.maxLength(1000)]],
      documents: [null]
    });
  }

  onSubmit(): void {
    if (this.applicationForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      this.submitError = '';
      this.submitSuccess = '';

      const applicationData: ApplicationRequest = {
        details: this.applicationForm.get('details')?.value,
        documentsPath: '' // Handle file upload separately
      };

      this.applicationService.submitApplication(applicationData).subscribe({
        next: (response) => {
          this.isSubmitting = false;
          this.submitSuccess = 'Demande soumise avec succès!';
          // Redirect to dashboard after 2 seconds
          setTimeout(() => {
            this.router.navigate(['/applicant/dashboard']);
          }, 2000);
        },
        error: (error) => {
          this.isSubmitting = false;
          this.submitError = error.error?.message || 'Erreur lors de la soumission de la demande';
        }
      });
    } else {
      this.markFormGroupTouched();
    }
  }

  private markFormGroupTouched(): void {
    Object.keys(this.applicationForm.controls).forEach(key => {
      const control = this.applicationForm.get(key);
      control?.markAsTouched();
    });
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      // Handle file upload logic here
      console.log('File selected:', file.name);
    }
  }

  // Getter methods for easy access in template
  get details() { return this.applicationForm.get('details'); }
  get documents() { return this.applicationForm.get('documents'); }
}