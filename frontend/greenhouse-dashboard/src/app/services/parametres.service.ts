import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametre } from '../models/parametre.model';

@Injectable({
  providedIn: 'root'
})
export class ParametresService {

  private apiUrl = '/environment-service/api/parametres';
  private enumUrl = '/environment-service/api/enums/parametre-types';

  constructor(private http: HttpClient) {}

  // Récupère tous les paramètres
  getAll(): Observable<Parametre[]> {
    return this.http.get<Parametre[]>(this.apiUrl);
  }

  // Sauvegarde un paramètre
  save(param: Parametre): Observable<Parametre> {
    return this.http.post<Parametre>(this.apiUrl, param);
  }

  // Récupère les types d'enum depuis le backend
  getTypes(): Observable<string[]> {
    return this.http.get<string[]>(this.enumUrl);
  }
}
