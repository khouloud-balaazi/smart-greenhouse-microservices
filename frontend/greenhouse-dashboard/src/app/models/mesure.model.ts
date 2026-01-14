import { Parametre } from './parametre.model';

export interface Mesure {
  id: number;
  parametre: Parametre;
  valeur: number;
  dateMesure: string;
}