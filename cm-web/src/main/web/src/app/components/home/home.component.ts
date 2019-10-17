import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../websocket/websocket.service';

import { Tiempos } from '../../models/tiempos.model';

import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  tiempos: Tiempos[];
  // portId: number = null;
  // tiempo: Tiempos;

  private subscription: Subscription;

  public crowd: Number[];

  ngOnInit(): void {
    
    
    
    this.webSocketService.connectWS();

    this.subscription = this.webSocketService.observablePeople
    .subscribe(item => {
      // var tiempo = new Tiempos();
      // tiempo.id = item[0];
      // console.log(item[1]);
      // tiempo.t1 = item[1];
      // tiempo.t2 = item[2];
      // tiempo.t3 = item[3];
      // tiempo.t4 = item[4];
      // tiempo.t5 = item[5];
      // this.tiempos.push(tiempo);

      this.crowd = item;
    

    // console.log('crowd: ' + this.crowd);
    });

  }
  constructor(private webSocketService: WebSocketService) {
    this.tiempos = new Array<Tiempos>();
  }

}
