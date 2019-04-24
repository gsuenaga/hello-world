import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { ConfiguracionComponent } from './components/configuracion/configuracion.component';
import { SerialPortService } from './components/configuracion/serialport.service';
import { SerialPortComponent } from './components/configuracion/serialPort.component';
import {HttpClientModule} from '@angular/common/http';

import { ROUTES } from './app.routes';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ConfiguracionComponent,
    HomeComponent,
    SerialPortComponent
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
