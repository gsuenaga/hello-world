import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../configuracion/websocket.service';

import { Tiempos } from '../../models/tiempos.model';

import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  tiempos: Tiempos[];
  portId: number = null;
  tiempo: Tiempos;

  private subscription: Subscription;

  public crowd: String[];

  ngOnInit(): void {
    this.webSocketService.connectWS();

    this.subscription = this.webSocketService.observablePeople
    .subscribe(item => {
    this.crowd = item;
    console.log('crowd: ' + this.crowd);
    });

  }
  constructor(private webSocketService: WebSocketService) {
  }

  // private fieldArray: Array<any> = [];
  // private newAttribute: any = {};

  // addFieldValue() {
  //     this.fieldArray.push(this.newAttribute)
  //     this.newAttribute = {};
  // }

  // deleteFieldValue(index) {
  //     this.fieldArray.splice(index, 1);
  // }
}
