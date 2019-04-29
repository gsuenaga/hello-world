import { Routes } from '@angular/router';
import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ConfiguracionComponent } from './components/configuracion/configuracion.component';
import { SerialPortComponent } from './components/configuracion/serialPort.component';

export const ROUTES: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'search', component: ConfiguracionComponent },
    { path: 'ports', component: SerialPortComponent },
    { path: '', pathMatch: 'full', redirectTo: 'home' },
    { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

// @NgModule({
//     imports: [
//       RouterModule.forRoot(ROUTES)
//     ],
//     exports: [
//       RouterModule
//     ],
//     declarations: []
//   })
//   export class AppRoutingModule { }