import { Injectable } from '@angular/core';
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

  private people: Array<number>;
  public observablePeople: BehaviorSubject<number[]>;
  private message2: Array<String>;


  constructor(private http: HttpClient) {
    this.people = new Array<number>();
    this.observablePeople = new BehaviorSubject<number[]>(this.people);
    this.message2 = new Array<String>();
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
    const people2 = new Array<number>();
    console.log('antes : ' + message);
    this.message2 = message.replace(/\,/gi, ':').replace('{', '').replace('}', '').split(':');
    console.log('despues : ' + this.message2);
    this.message2.forEach(function (value) {

      if (value.search(/medicion|id/gi) === -1) {
        people2.push(+value.replace(/\"/gi, ''));
      }

    });
    this.people = people2;
    this.eventChange();
  }

}

