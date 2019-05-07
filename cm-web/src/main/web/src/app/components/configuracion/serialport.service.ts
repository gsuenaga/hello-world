import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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

  // public getSerialPortSelected() {
  //   console.log('getSerialPortSelected');
  //   console.log(this.port);
  //   return this.port;
  // }

  // public setSerialPortSelected(id) {
  //   console.log('setSerialPortSelected');
  //   console.log(id);
  //   this.port.id = id;
  //   // this.port = this.ports.find(x => x.id === portId);
  //   console.log('se guardo');
  //   console.log(this.port.id);
  // }

  // public setSerialPorts(ports: SerialPort[]) {
  //   this.ports = ports;
  // }
}
