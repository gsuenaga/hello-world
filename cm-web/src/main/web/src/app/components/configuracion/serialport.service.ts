import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { SerialPort } from '../../models/serialport.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class SerialPortService {

  constructor(private http: HttpClient) {}

  private serialPortUrl = 'http://localhost:8080/cm-parent/cm-server/ports';

  public getPorts() {
    return this.http.get<SerialPort[]>(this.serialPortUrl);
  }

/*   public deleteUser(user) {
    return this.http.delete(this.userUrl + "/"+ user.id);
  }
 */
  public updatePort(port) {
    return this.http.post<SerialPort>(this.serialPortUrl, port);
  }

}
