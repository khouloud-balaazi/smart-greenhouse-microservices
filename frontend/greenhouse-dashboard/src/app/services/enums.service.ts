import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnumsService {

  constructor(private http: HttpClient) {}

  getParametreTypes(): Observable<string[]> {
    return this.http.get<string[]>(
      '/environment-service/api/enums/parametre-types'
    );
  }

  getEquipementTypes(): Observable<string[]> {
    return this.http.get<string[]>(
      '/controle-service/api/enums/equipement-types'
    );
  }

  getActionTypes(): Observable<string[]> {
    return this.http.get<string[]>(
      '/controle-service/api/enums/action-types'
    );
  }
}
