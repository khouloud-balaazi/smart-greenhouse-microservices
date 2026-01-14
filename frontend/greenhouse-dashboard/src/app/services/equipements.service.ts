import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipement } from '../models/equipement.model';
import { EquipementType, EtatEquipement } from '../models/enums.model';

@Injectable({
  providedIn: 'root'
})
export class EquipementsService {
  private apiUrl = '/controle-service/api/equipements';

  constructor(private http: HttpClient) {}

  // Récupère tous les équipements
  getAll(): Observable<Equipement[]> {
    return this.http.get<Equipement[]>(this.apiUrl);
  }

  // Ajoute un équipement
  add(equip: { type: EquipementType; etat: EtatEquipement }): Observable<Equipement> {
    return this.http.post<Equipement>(this.apiUrl, equip);
  }

  // Met à jour l'état d'un équipement
  updateEtat(id: number, etat: EtatEquipement): Observable<Equipement> {
    return this.http.put<Equipement>(`${this.apiUrl}/${id}?etat=${etat}`, {});
  }
}
