import { ParametreType } from './enums.model';

export interface Parametre {
  id?: number;
  type: ParametreType | '';
  seuilMin: number;
  seuilMax: number;
}