import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';
import { ParametresService } from '../../services/parametres.service';
import { EquipementsService } from '../../services/equipements.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  nbParametres = 0;
  nbEquipements = 0;
  lastValue: number | string = '--';
  averageValue: number | string = '--';
  alerts: string[] = [];

  constructor(
    private dashboardService: DashboardService,
    private parametresService: ParametresService,
    private equipementsService: EquipementsService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  refresh(): void {
    this.loadData();
  }

  loadData(): void {

    // Paramètres
    this.parametresService.getAll().subscribe((data: any[]) => {
      this.nbParametres = data.length;
    });

    // Équipements
    this.equipementsService.getAll().subscribe((data: any[]) => {
      this.nbEquipements = data.length;
    });

    // Mesures
    this.dashboardService.getMesures().subscribe((data: any[]) => {
      if (data.length > 0) {
        const last = data[data.length - 1];
        this.lastValue = last.valeur;

        const sum = data.reduce((s: number, m: any) => s + m.valeur, 0);
        this.averageValue = (sum / data.length).toFixed(2);

        // Alertes simples
        this.alerts = [];
        if (last.valeur > 30) {
          this.alerts.push('⚠️ Température élevée');
        }
      }
    });
  }
}
