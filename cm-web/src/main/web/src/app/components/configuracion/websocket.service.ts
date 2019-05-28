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
    private stompClient = null;
    greetings: string[] = [];
    disabled = true;
    constructor(private http: HttpClient) {}

    connectWS() {
      if (this.disabled) {
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

        setConnected(connected: boolean) {
            this.disabled = !connected;

            if (connected) {
              this.greetings = [];
            }
          }

          showGreeting(message) {
            this.greetings.push(message);
          }
  }
