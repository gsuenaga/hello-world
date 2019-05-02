import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SerialPortComponent } from './components/configuracion/serialport.component';
import { ConfiguracionComponent } from './components/configuracion/configuracion.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { SerialPortService } from './components/configuracion/serialport.service';

import { ROUTES } from './app.routes';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ConfiguracionComponent,
    SerialPortComponent,
    NavbarComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot( ROUTES, { useHash: true } )
  ],
  providers: [SerialPortService],
  bootstrap: [AppComponent]
})
export class AppModule { }
