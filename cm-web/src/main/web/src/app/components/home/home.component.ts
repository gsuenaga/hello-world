import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../websocket/websocket.service';

import { Tiempos } from '../../models/tiempos.model';
import { ReglaService } from '../configuracion/regla.service';
import { Regla } from '../../models/regla.model';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  public tiempos: Tiempos[];
  // portId: number = null;
  // tiempo: Tiempos;
  public objetoRegla: Regla[];

  private subscription: Subscription;

  // public crowd: Number[];

  ngOnInit(): void {

    this.reglaService.getDistancias()
    .subscribe( data => {
      this.objetoRegla = data;
      console.log('distancias' + data[0].distancia);
    });

    this.webSocketService.connectWS();

    this.subscription = this.webSocketService.observablePeople
    .subscribe(item => {
      // this.crowd = item;
      // console.log('crowd: ' + this.crowd);
      if (item[0] != null) {
        const tiempo = new Tiempos();
        tiempo.id = item[0];
        tiempo.t1 = item[1];
        tiempo.t2 = item[2];
        tiempo.t3 = item[3];
        tiempo.t4 = item[4];
        tiempo.t5 = item[5];
        tiempo.v1 = Math.round(item[1] / +this.objetoRegla[0].distancia * 100) / 100;
        tiempo.v2 = Math.round(item[2] / +this.objetoRegla[1].distancia * 100) / 100;
        tiempo.v3 = Math.round(item[3] / +this.objetoRegla[2].distancia * 100) / 100;
        tiempo.v4 = Math.round(item[4] / +this.objetoRegla[3].distancia * 100) / 100;
        tiempo.v5 = Math.round(item[5] / +this.objetoRegla[4].distancia * 100) / 100;
        this.tiempos.push(tiempo);
        console.log(this.tiempos);
        console.log(+this.objetoRegla[0]);
      }

    });


  }
  constructor(private webSocketService: WebSocketService, private reglaService: ReglaService) {
    this.tiempos = new Array<Tiempos>();
  }

}
