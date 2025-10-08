export interface User {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  role: 'APPLICANT' | 'ADMIN';
  dateCreation?: Date;
  actif?: boolean;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  nom: string;
  prenom: string;
  email: string;
  password: string;
  nomEtablissement: string;
  adresse: string;
  telephone?: string;
  emailEtablissement?: string;
}

export interface AuthResponse {
  accessToken: string;
  tokenType: string;
  id: number;
  email: string;
  nom: string;
  prenom: string;
  role: 'APPLICANT' | 'ADMIN';
}