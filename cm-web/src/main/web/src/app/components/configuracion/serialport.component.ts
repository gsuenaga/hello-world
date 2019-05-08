import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SerialPort } from '../../models/serialport.model';
import { SerialPortService } from './serialport.service';

@Component({
  selector: 'app-serialport',
  templateUrl: './serialport.component.html'
})

export class SerialPortComponent implements OnInit {

  ports: SerialPort[];
  portId: number = null;
  port: SerialPort;

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

  // onSelect(port) {
  //   this.portId = port.id;
  //   this.serialportService.setPortSelected()
  //   function getDimensionsByFilter(id){
  //     return port.filter(x => x.id === id);
  //   }
  }
}


