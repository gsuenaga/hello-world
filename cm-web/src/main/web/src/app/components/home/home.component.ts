import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../configuracion/websocket.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  ngOnInit(): void {
    this.webSocketService.connectWS();
  }
  constructor(private webSocketService: WebSocketService) {
  }

}
