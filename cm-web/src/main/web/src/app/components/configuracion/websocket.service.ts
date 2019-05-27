import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';



const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  @Injectable()
  export class WebSocketService {


    constructor(private http: HttpClient) {}

    private serialPortUrl = 'http://localhost:8090/ports';

    connectWS() {
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

  }
