import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SerialPort } from '../../models/SerialPort.model';
import { SerialPortService } from './serialport.service';

@Component({
  selector: 'app-serialport',
  templateUrl: './serialport.component.html',
  styles: []
})
export class SerialPortComponent implements OnInit {

  ports: SerialPort[];

  constructor(private router: Router, private serialportService: SerialPortService) {

  }

  ngOnInit() {
     console.log('paso por ngOnInit');
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

}


