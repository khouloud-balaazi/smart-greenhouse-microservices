import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ParametresComponent } from './components/parametres/parametres.component';
import { EquipementsComponent } from './components/equipements/equipements.component';
import { ActionsComponent } from './components/actions/actions.component';
import { MesuresComponent } from './components/mesures/mesures.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'parametres', component: ParametresComponent },
  { path: 'equipements', component: EquipementsComponent },
  { path: 'actions', component: ActionsComponent },
  { path: 'mesures', component: MesuresComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: '/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
