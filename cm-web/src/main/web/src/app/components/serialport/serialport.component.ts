import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SerialPort } from '../../models/serialport.model';
import { SerialPortService } from './serialport.service';

import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Component({
  selector: 'app-serialport',
  templateUrl: './serialport.component.html'
})

export class SerialPortComponent implements OnInit {

  public ports: SerialPort[];
  portId: number = null;
  public portSelected: SerialPort;

  private stompClient = null;
  greetings: string[] = [];
  disabled = true;
  estado = 'Desconectado';
  showActions = false;

  constructor(private router: Router, private serialportService: SerialPortService) {

  }

  ngOnInit() {
    this.serialportService.getPorts()
      .subscribe( data => {
        this.ports = data;
        console.log(data);
      });
  }

  updatePort(port: SerialPort): void {
    this.serialportService.updatePort(port)
      .subscribe( data => {
        this.ports = this.ports.filter(u => u !== port);
      });
  }

  updateShowActions(port: SerialPort): void {
    if (this.estado === 'Conectado') {
      this.showActions = true;
    } else {
      this.showActions = false;
    }
    this.portSelected = port;
  }

  connectPort(port: SerialPort): void {
    this.serialportService.connectPort(port)
      .subscribe( data => {
        this.estado = data;
        this.updateShowActions(port);
          console.log(port.port + ':' + data);
      },
      response => {
          console.log('POST call in error', response);
      },
      () => {
          console.log('The POST observable is now completed.');
      });

  }

  disconnectPort(port: SerialPort): void {
    this.serialportService.disconnectPort(port)
      .subscribe( data => {
        this.estado = data;
        this.updateShowActions(port);
          console.log(data);
      },
      response => {
          console.log('POST call in error', response);
      },
      () => {
          console.log('The POST observable is now completed.');
      });

  }
}
