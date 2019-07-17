import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { SerialPort } from '../../models/serialport.model';
import { SerialPortService } from '../serialport/serialport.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styles: []
})
export class DetailComponent implements OnInit {

  // port: SerialPort = this.serialPortService.getSerialPortSelected();
  public port: SerialPort;


  constructor(private route: ActivatedRoute, private serialPortService: SerialPortService) {
    this.port = new SerialPort();
  }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      console.log(params.get('id'));
      this.port.id = params.get('id');
      this.port.port = params.get('port');
      this.port.baudrate = params.get('baudrate');
      this.port.databits = params.get('databits');
      this.port.stopbits = params.get('stopbits');
    });
  }

  updatePort(): void {
    this.serialPortService.updatePort(this.port)
        .subscribe( data => {
          alert('Puerto actualizado.');
        });

  }


}

