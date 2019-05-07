import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SerialPortComponent } from './components/configuracion/serialport.component';
import { DetailComponent } from './components/configuracion/detail.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { SerialPortService } from './components/configuracion/serialport.service';

import { AppRoutingModule } from './app.routes';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DetailComponent,
    SerialPortComponent,
    NavbarComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
    // RouterModule.forRoot( ROUTES, { useHash: true } )
  ],
  providers: [SerialPortService],
  bootstrap: [AppComponent]
})
export class AppModule { }
