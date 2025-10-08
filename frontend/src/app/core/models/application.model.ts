export interface Etablissement {
  id: number;
  nom: string;
  adresse: string;
  telephone?: string;
  email?: string;
  dateCreation?: Date;
}

export interface DemandeAdhesion {
  id: number;
  dateSoumission: Date;
  statut: 'EN_ATTENTE' | 'VALIDEE' | 'REJETEE';
  details: string;
  commentaireAdmin?: string;
  dateTraitement?: Date;
  documentsPath?: string;
  etablissement?: Etablissement;
  user?: {
    id: number;
    nom: string;
    prenom: string;
    email: string;
  };
  adminTraitant?: {
    id: number;
    nom: string;
    prenom: string;
  };
}

export interface ApplicationRequest {
  details: string;
  documentsPath?: string;
}

export interface ApplicationResponse {
  message: string;
}