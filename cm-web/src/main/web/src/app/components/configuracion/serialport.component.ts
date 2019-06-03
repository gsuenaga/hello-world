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

  ports: SerialPort[];
  portId: number = null;
  port: SerialPort;

  private stompClient = null;
  greetings: string[] = [];
  disabled = true;
  estado = 'Desconectado';

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

  connectPort(port: SerialPort): void {
    this.serialportService.connectPort(port)
      .subscribe( data => {
        this.estado = data;
          console.log(data);
      },
      response => {
          console.log('POST call in error', response);
      },
      () => {
          console.log('The POST observable is now completed.');
      });

  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  showGreeting(message) {
    this.greetings.push(message);
  }

  connectWS(port: SerialPort) {
  const socket = new SockJS('http://localhost:8090/gkz-stomp-endpoint');
  this.stompClient = Stomp.over(socket);

  const _this = this;
  this.stompClient.connect({}, function (frame) {
    _this.setConnected(true);
    console.log('Connected: ' + frame);

    _this.stompClient.subscribe('/topic/hi', function (hello) {
      _this.showGreeting(JSON.parse(hello.body).greeting);
    });
  });
  }

  connectWS2() {
    const socket = new SockJS('http://localhost:8090/gkz-stomp-endpoint');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.stompClient.subscribe('/topic/hi', function (hello) {
        _this.showGreeting(JSON.parse(hello.body).greeting);
      });
    });
    }

  sendName(port: SerialPort) {
    this.stompClient.send(
      '/gkz/hello',
      {},
      JSON.stringify({ 'name': port })
    );
  }

  // onSelect(port) {
  //   this.portId = port.id;
  //   this.serialportService.setPortSelected()
  //   function getDimensionsByFilter(id){
  //     return port.filter(x => x.id === id);
  //   }
  // }
}
