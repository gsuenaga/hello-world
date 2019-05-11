import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SerialPort } from '../../models/serialport.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class SerialPortService {

  port: SerialPort;
  ports: SerialPort[];

  constructor(private http: HttpClient) {}

  private serialPortUrl = 'http://localhost:8090/ports';

  public getPorts() {
    return this.http.get<SerialPort[]>(this.serialPortUrl + '/portList');
  }

/*   public deleteUser(user) {
    return this.http.delete(this.userUrl + "/"+ user.id);
  }
 */
  public updatePort(port) {
    return this.http.post<SerialPort>(this.serialPortUrl, port);
  }

  public connectPort(port) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    return this.http.post<string>(this.serialPortUrl + '/connect', port, { headers,  responseType: 'text' as 'json' });
  }

 
} 

  

