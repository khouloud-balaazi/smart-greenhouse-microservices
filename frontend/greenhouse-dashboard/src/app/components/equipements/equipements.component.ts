import { Component, OnInit } from '@angular/core';
import { EquipementsService } from '../../services/equipements.service';
import { Equipement } from '../../models/equipement.model';
import { EquipementType, EtatEquipement } from '../../models/enums.model';

@Component({
  selector: 'app-equipements',
  templateUrl: './equipements.component.html',
  styleUrls: ['./equipements.component.css']
})
export class EquipementsComponent implements OnInit {

  equipements: Equipement[] = [];

  types: EquipementType[] = ['VENTILATEUR', 'POMPE', 'CHAUFFAGE'];
  etats: EtatEquipement[] = ['OFF', 'ON', 'AUTO'];

  newEquip: { type: EquipementType | ''; etat: EtatEquipement } = { type: '', etat: 'OFF' };

  constructor(private equipService: EquipementsService) { }

  ngOnInit(): void {
    this.loadEquipements();
  }

  // Charger les équipements depuis le backend
  loadEquipements(): void {
    this.equipService.getAll().subscribe(data => this.equipements = data);
  }

  // Ajouter un équipement
  addEquipement(): void {
    if (!this.newEquip.type) {
      alert('Veuillez sélectionner un type');
      return;
    }

    // Transformer pour correspondre à l'interface attendue par le service
    const payload = {
      type: this.newEquip.type as EquipementType,
      etat: this.newEquip.etat
    };

    this.equipService.add(payload).subscribe(() => {
      this.loadEquipements();
      this.resetForm();
    });
  }

  // Réinitialiser le formulaire
  resetForm(): void {
    this.newEquip = { type: '', etat: 'OFF' };
  }

  // Changer état directement depuis le tableau
  changeEtat(equip: Equipement, etat: EtatEquipement): void {
    this.equipService.updateEtat(equip.id!, etat).subscribe(() => {
      this.loadEquipements();
    });
  }
  onEtatChange(equip: Equipement, event: Event): void {
  const select = event.target as HTMLSelectElement;
  const etat = select.value as EtatEquipement;
  this.changeEtat(equip, etat);
}

}
