// src/app/services/mesures.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ParametreType } from '../models/enums.model';

interface MesurePayload {
  type: ParametreType;
  valeur: number;
}

@Injectable({
  providedIn: 'root'
})
export class MesuresService {

  private apiUrl = '/environment-service/api/mesures'; 

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  add(payload: MesurePayload): Observable<any> {
    return this.http.post<any>(this.apiUrl, payload);
  }

  getByParametre(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/parametre/${id}`);
  }
}
