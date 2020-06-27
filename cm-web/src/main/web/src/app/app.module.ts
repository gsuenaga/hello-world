import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SerialPortComponent } from './components/serialport/serialport.component';
import { DetailComponent } from './components/configuracion/detail.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { SerialPortService } from './components/serialport/serialport.service';
import { WebSocketService } from './components/websocket/websocket.service';

import { AppRoutingModule } from './app.routes';
import { ReglaComponent } from './components/configuracion/regla.component';
import { ReglaService } from './components/configuracion/regla.service';


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
  providers: [SerialPortService, WebSocketService, ReglaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
