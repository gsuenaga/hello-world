import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Regla } from '../../models/regla.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ReglaService {

  // distancia: Regla;
  // distancias: Regla[];

  constructor(private http: HttpClient) {}

  private reglaUrl = 'http://localhost:8090/regla';

  public getDistancias() {
    return this.http.get<Regla[]>(this.reglaUrl + '/reglaList');
  }

  public updateDistancia(regla) {
    return this.http.post<Regla>(this.reglaUrl, regla);
  }

  public resetDistancias() {
    return this.http.get<Regla[]>(this.reglaUrl + '/reglaReset');
  }

  public updateRegla(distancias) {
    return this.http.post<Regla>(this.reglaUrl, distancias);
  }

}



