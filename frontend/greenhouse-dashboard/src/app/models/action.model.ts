// action.model.ts
import { Equipement } from './equipement.model';
import { Parametre } from './parametre.model';
import { ActionType } from './enums.model';

export interface Action {
  id?: number;
  equipement: Equipement;   // objet Equipement complet
  typeAction: ActionType | '';
  dateExecution?: string;   // ISO string
  parametre?: Parametre;    // récupéré via Feign (Transient)
  parametreId?: number;     // pour POST/PUT
}
