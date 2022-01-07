import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavMenuComponent } from './nav-menu/nav-menu.component';
import { HomeComponent } from './home/home.component';
import { AcercaComponent } from './acerca/acerca.component';
import { LeaguesComponent } from './leagues/leagues.component';
import { CreateComponent } from './leagues/create/create.component';
import { EditComponent } from './leagues/edit/edit.component';
import { DetailsComponent } from './leagues/details/details.component';

@NgModule({
  declarations: [
    AppComponent,
    NavMenuComponent,
    HomeComponent,
    AcercaComponent,
    LeaguesComponent,
    CreateComponent,
    EditComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'ng-cli-universal' }),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', component: HomeComponent, pathMatch: 'full' },
      { path: 'acerca', component: AcercaComponent },
      { path: 'ligas', component: LeaguesComponent },
      { path: 'ligas/create', component: CreateComponent },
      { path: 'ligas/:leagueId/edit', component: EditComponent },
      { path: 'ligas/:leagueId/details', component: DetailsComponent},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
