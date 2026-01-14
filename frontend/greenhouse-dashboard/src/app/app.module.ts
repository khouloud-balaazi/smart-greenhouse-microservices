import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // <-- IMPORTANT

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ParametresComponent } from './components/parametres/parametres.component';
import { EquipementsComponent } from './components/equipements/equipements.component';
import { ActionsComponent } from './components/actions/actions.component';
import { MesuresComponent } from './components/mesures/mesures.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ParametresComponent,
    EquipementsComponent,
    ActionsComponent,
    MesuresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule   // <-- AJOUTÉ
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
