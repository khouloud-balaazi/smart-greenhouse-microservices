import { Component, OnInit } from '@angular/core';
import { ParametresService } from '../../services/parametres.service';
import { Parametre } from '../../models/parametre.model';
import { ParametreType } from '../../models/enums.model';

@Component({
  selector: 'app-parametres',
  templateUrl: './parametres.component.html',
  styleUrls: ['./parametres.component.css']
})
export class ParametresComponent implements OnInit {

  parametres: Parametre[] = [];
  allTypes: ParametreType[] = ['TEMPERATURE', 'HUMIDITE', 'LUMINOSITE']; // tous les types possibles
  typesDisponibles: ParametreType[] = []; // types filtrés pour le dropdown

  newParam: { type: ParametreType | ''; seuilMin: number; seuilMax: number } = {
    type: '',
    seuilMin: 0,
    seuilMax: 0
  };

  constructor(private paramService: ParametresService) {}

  ngOnInit(): void {
    this.loadParametres();
  }

  // Charger les paramètres existants
  loadParametres(): void {
    this.paramService.getAll().subscribe(data => {
      this.parametres = data;
      this.updateTypesDisponibles();
    });
  }

  // Met à jour les types disponibles pour le dropdown
  updateTypesDisponibles(): void {
    const typesUtilises = this.parametres.map(p => p.type);
    this.typesDisponibles = this.allTypes.filter(t => !typesUtilises.includes(t));
  }

  // Sauvegarde un paramètre
  saveParametre(): void {
    if (!this.newParam.type) {
      alert('Veuillez sélectionner un type');
      return;
    }

    const payload = {
      type: this.newParam.type,
      seuilMin: this.newParam.seuilMin,
      seuilMax: this.newParam.seuilMax
    };

    this.paramService.save(payload).subscribe({
      next: () => {
        this.loadParametres();
        this.resetForm();
      },
      error: (err) => {
        alert(err.error || 'Erreur lors de l’enregistrement');
      }
    });
  }

  resetForm(): void {
    this.newParam = { type: '', seuilMin: 0, seuilMax: 0 };
    this.updateTypesDisponibles();
  }
}
