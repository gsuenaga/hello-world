import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReglaService } from './regla.service';
import { Regla } from '../../models/regla.model';
import { Stomp } from '@stomp/stompjs';
// import SockJS from 'sockjs-client';

@Component({
  selector: 'app-regla',
  templateUrl: './regla.component.html',
  styleUrls: ['./regla.component.css']
})

export class ReglaComponent implements OnInit {

  // distancia: Regla;
  distancias: Regla[];
  editField: string;

  constructor(private router: Router, private reglaService: ReglaService) {
  }

  ngOnInit() {
    this.reglaService.getDistancias()
    .subscribe( data => {
      this.distancias = data;
      console.log(data);
    });
  }

  updateList(id: number, property: Regla, event: any) {
    const editField = event.target.textContent;
    this.distancias[id].distancia = editField;
    // this.distancias[id][property] = editField;
  }

  updateRegla(): void {
    this.reglaService.updateRegla(this.distancias)
        .subscribe( data => {
          alert('Regla actualizada.');
        });
  }

  changeValue(id: number, property: Regla, event: any) {
    this.editField = event.target.textContent;
  }
}
