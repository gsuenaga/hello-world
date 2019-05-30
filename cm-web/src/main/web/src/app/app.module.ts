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
import { WebSocketService } from './components/configuracion/websocket.service';

import { AppRoutingModule } from './app.routes';
import { ReglaComponent } from './components/configuracion/regla.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DetailComponent,
    SerialPortComponent,
    NavbarComponent,
    ReglaComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule

    // RouterModule.forRoot( ROUTES, { useHash: true } )
  ],
  providers: [SerialPortService, WebSocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
