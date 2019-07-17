import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  @Injectable()
  export class WebSocketService {
    private stompClient = null;
    greetings: string[] = [];
    disabled = true;

    private people: Array<String>;
    public observablePeople: BehaviorSubject<String[]>;

    constructor(private http: HttpClient) {
      this.people = new Array<String>();
      this.observablePeople = new BehaviorSubject<String[]>(this.people);
    }

    eventChange() {
      this.observablePeople.next(this.people);
    }

    connectWS() {
      if (this.disabled) {
        const socket = new SockJS('http://localhost:8090/gkz-stomp-endpoint');
        this.stompClient = Stomp.over(socket);

        const _this = this;
        this.stompClient.connect({}, function (frame) {
          _this.setConnected(true);
          console.log('Connected: ' + frame);

          _this.stompClient.subscribe('/topic/hi', function (hello) {
            // _this.showGreeting(JSON.parse(hello.body).greeting);
            _this.showGreeting(hello.body);
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
      // console.log('antes : ' + message);
      // message = message.replace('[', '\\r').replace(']', '\\r').replace(',', '').replace('""', '').replace('"', '');
      // message = message.split('\\r').filter(item => item !== '' && item !== '"');
      // console.log('despues : ' + message);
      this.greetings.push(message);

      this.people.push(message);
      this.eventChange();
    }

  }

