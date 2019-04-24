import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SerialPort } from '../../models/serialport.model';
import { SerialPortService } from './serialport.service';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html'
})
export class ConfiguracionComponent implements OnInit {

  serialPort: SerialPort = new SerialPort();

  constructor(private router: Router, private serialPortService: SerialPortService) {

  }

  ngOnInit() {
    // listar puertos
  }

  updatePort(): void {
    this.serialPortService.updatePort(this.serialPort)
        .subscribe( data => {
          alert('Puerto actualizado.');
        });

  }
}
