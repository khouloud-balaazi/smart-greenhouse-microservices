// actions.component.ts
import { Component, OnInit } from '@angular/core';
import { ActionsService } from '../../services/actions.service';
import { EquipementsService } from '../../services/equipements.service';
import { ParametresService } from '../../services/parametres.service';
import { Action } from '../../models/action.model';
import { Equipement } from '../../models/equipement.model';
import { Parametre } from '../../models/parametre.model';
import { ActionType } from '../../models/enums.model';

@Component({
  selector: 'app-actions',
  templateUrl: './actions.component.html',
  styleUrls: ['./actions.component.css']
})
export class ActionsComponent implements OnInit {

  actions: Action[] = [];
  equipements: Equipement[] = [];
  parametres: Parametre[] = [];
  actionTypes: ActionType[] = ['DEMARRER', 'ARRETER', 'REGULER'];

  newAction: {
    equipementId?: number;
    typeAction?: ActionType;
    parametreId?: number;
  } = {};

  constructor(
    private actionsService: ActionsService,
    private equipService: EquipementsService,
    private paramService: ParametresService
  ) {}

  ngOnInit(): void {
    this.loadEquipements();
    this.loadParametres(); // Charger d'abord les paramètres, puis les actions
  }

  // Charger tous les équipements
  loadEquipements(): void {
    this.equipService.getAll().subscribe(data => this.equipements = data);
  }

  // Charger tous les paramètres et ensuite les actions
  loadParametres(): void {
    this.paramService.getAll().subscribe(data => {
      this.parametres = data;
      this.loadActions(); // actions chargées après parametres
    });
  }

  // Charger toutes les actions depuis le back
  loadActions(): void {
    this.actionsService.getAll().subscribe(data => {
      // Compléter chaque action avec son paramètre depuis la liste locale
      this.actions = data.map(a => ({
        ...a,
        parametre: a.parametreId ? this.parametres.find(p => p.id === a.parametreId) : undefined
      }));
    });
  }

  // Ajouter une action
  addAction(): void {
    const equipementId = this.newAction.equipementId;
    const typeAction = this.newAction.typeAction;
    const parametreId = this.newAction.parametreId;

    if (!equipementId || !typeAction || !parametreId) {
      alert('Veuillez remplir tous les champs');
      return;
    }

    const actionToAdd = {
      equipement: { id: equipementId },
      typeAction: typeAction,
      parametreId: parametreId
    };

    this.actionsService.add(actionToAdd).subscribe(() => {
      this.loadActions();
      this.resetForm();
    });
  }

  // Réinitialiser le formulaire
  resetForm(): void {
    this.newAction = {};
  }

  // Récupérer le type du paramètre pour affichage
  getParametreType(a: Action): string {
    return a.parametre?.type ?? '';
  }
}
