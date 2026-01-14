// src/app/components/mesures/mesures.component.ts
import { Component, OnInit } from '@angular/core';
import { MesuresService } from '../../services/mesures.service';
import { ParametresService } from '../../services/parametres.service';
import { Parametre } from '../../models/parametre.model';
import { ParametreType } from '../../models/enums.model'; // ✅ correction ici

interface Stat {
  dernieres: number[];
  derniere: number;
  moyenne: number;
  seuilMin: number;
  seuilMax: number;
}

@Component({
  selector: 'app-mesures',
  templateUrl: './mesures.component.html',
  styleUrls: ['./mesures.component.css']
})
export class MesuresComponent implements OnInit {

  mesures: any[] = [];
  parametres: Parametre[] = [];
  stats: Record<string, Stat> = {}; // typer correctement

  newMesure: { type?: ParametreType | ''; valeur?: number; dateMesure?: string } = {};

  constructor(
    private mesureService: MesuresService,
    private paramService: ParametresService
  ) {}

  ngOnInit(): void {
    this.loadParametres();
    this.loadMesures();
  }

  loadParametres(): void {
    this.paramService.getAll().subscribe(data => this.parametres = data);
  }

  loadMesures(): void {
    this.mesureService.getAll().subscribe(data => {
      this.mesures = data;

      // Calcul stats
      this.stats = {};
      data.forEach(m => {
        const type = m.parametre.type;
        if (!this.stats[type]) {
          this.stats[type] = { dernieres: [], seuilMin: m.parametre.seuilMin, seuilMax: m.parametre.seuilMax, derniere: 0, moyenne: 0 };
        }
        this.stats[type].dernieres.push(m.valeur);
      });

      for (let type of Object.keys(this.stats)) {
        const vals = this.stats[type].dernieres;
        this.stats[type].derniere = vals[vals.length - 1];
        this.stats[type].moyenne = vals.reduce((a, b) => a + b, 0) / vals.length;
      }
    });
  }

  addMesure(): void {
    if (!this.newMesure.type || this.newMesure.valeur === undefined) {
      alert('Veuillez remplir tous les champs');
      return;
    }

    const payload = {
      type: this.newMesure.type,
      valeur: this.newMesure.valeur
    };

    this.mesureService.add(payload).subscribe(() => {
      this.newMesure = {};
      this.loadMesures();
    });
  }
}
