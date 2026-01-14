import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiUrl = '/environment-service/api/mesures';

  constructor(private http: HttpClient) {}

  getMesures(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
