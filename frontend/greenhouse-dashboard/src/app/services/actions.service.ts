import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Action } from '../models/action.model';

@Injectable({
  providedIn: 'root'
})
export class ActionsService {
  private apiUrl = '/controle-service/api/actions';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Action[]> {
    return this.http.get<Action[]>(this.apiUrl);
  }

  add(action: { equipement: { id: number }; typeAction: string; parametreId: number; }): Observable<Action> {
    return this.http.post<Action>(this.apiUrl, action);
  }
}
