import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { ConfiguracionComponent } from './components/configuracion/configuracion.component';
import {SerialPortService} from './components/configuracion/serialport.service';

import { ROUTES } from './app.routes';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ConfiguracionComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot( ROUTES, { useHash: true } )
  ],
  providers: [SerialPortService],
  bootstrap: [AppComponent]
})
export class AppModule { }
