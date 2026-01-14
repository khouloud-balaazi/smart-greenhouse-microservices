// equipement.model.ts
import { EquipementType, EtatEquipement } from './enums.model';

export interface Equipement {
  id?: number;
  type: EquipementType | '';
  etat: EtatEquipement;
  derniereAction?: string; // ISO string du backend
}
