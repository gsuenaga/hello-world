import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ConfiguracionComponent } from './components/configuracion/configuracion.component';


export const ROUTES: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'search', component: ConfiguracionComponent },
    { path: '', pathMatch: 'full', redirectTo: 'home' },
    { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

