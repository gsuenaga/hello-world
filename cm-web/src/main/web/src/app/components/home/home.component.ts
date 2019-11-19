import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../websocket/websocket.service';

import { Tiempos } from '../../models/tiempos.model';
import { ReglaService } from '../configuracion/regla.service';
import { Regla } from '../../models/regla.model';
import { Subscription } from 'rxjs/internal/Subscription';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [ './home.component.css']
})
export class HomeComponent implements OnInit {

  public tiempos: Tiempos[];
  // portId: number = null;
  // tiempo: Tiempos;
  public objetoRegla: Regla[];

  private subscription: Subscription;

  private nroMed: any = 0;
  // public crowd: Number[];

  // Chart
  private intervalUpdate: any = null;
  public chart: any = null;

  ngOnInit(): void {

    this.reglaService.getDistancias()
      .subscribe(data => {
        this.objetoRegla = data;
        console.log('distancias' + data[0].distancia);
      });

    this.webSocketService.connectWS();

    this.subscription = this.webSocketService.observablePeople
      .subscribe(item => {
        // this.crowd = item;
        // console.log('crowd: ' + this.crowd);
        if (item[0] != null) {
          const tiempo = new Tiempos();
          tiempo.id = item[0];
          tiempo.t1 = item[1] / 1000;
          tiempo.t2 = item[2] / 1000;
          tiempo.t3 = item[3] / 1000;
          tiempo.t4 = item[4] / 1000;
          tiempo.t5 = item[5] / 1000;
          tiempo.v1 = Math.round(+this.objetoRegla[0].distancia * 1000000 / item[1]) / 1000;
          tiempo.v2 = Math.round(+this.objetoRegla[1].distancia * 1000000 / item[2]) / 1000;
          tiempo.v3 = Math.round(+this.objetoRegla[2].distancia * 1000000 / item[3]) / 1000;
          tiempo.v4 = Math.round(+this.objetoRegla[3].distancia * 1000000 / item[4]) / 1000;
          tiempo.v5 = Math.round(+this.objetoRegla[4].distancia * 1000000 / item[5]) / 1000;
          this.tiempos.push(tiempo);
          console.log(this.tiempos);
          console.log(+this.objetoRegla[0]);
          this.showData(tiempo);
        }

      });


    // Chart
    this.chart = new Chart('realtime', {
      type: 'line',
      data: {
        labels: [],
        datasets: [
          {
            label: 'Data',
            fill: false,
            data: [],
            backgroundColor: '#168ede',
            borderColor: '#168ede'
          },
          {
            label: 'Data',
            fill: false,
            data: [],
            backgroundColor: '#de162a',
            borderColor: '#de162a'
          },
          {
            label: 'Data',
            fill: false,
            data: [],
            backgroundColor: '#2ade16',
            borderColor: '#2ade16'
          }
        ]
      },
      options: {
        tooltips: {
          enabled: false
        },
        legend: {
          display: true,
          position: 'bottom',
          labels: {
            fontColor: 'white'
          }
        },
        scales: {
          yAxes: [{
            ticks: {
              fontColor: 'white'
            }
          }],
          xAxes: [{
            ticks: {
              fontColor: 'white',
              beginAtZero: true
            }
          }]
        }
      }
    });

    // this.showData();

    // this.intervalUpdate = setInterval(function () {
    //   this.showData();
    // }.bind(this), 500);

  }

  private showData(tiempo): void {
    // let chartTime: any = new Date();
    
    // const tiempo = this.tiempos.pop();
console.log( 'tiempo' + tiempo);

    // chartTime = chartTime.getHours() + ':' + ((chartTime.getMinutes() < 10) ? '0' + chartTime.getMinutes() : chartTime.getMinutes()) + ':' + ((chartTime.getSeconds() < 10) ? '0' + chartTime.getSeconds() : chartTime.getSeconds());
    // if (this.chart.data.labels.length > 15) {
    //   this.chart.data.labels.shift();
    //   this.chart.data.datasets[0].data.shift();
    // }
    this.chart.data.labels.push(tiempo.t1);
    this.chart.data.datasets[this.nroMed].data.push(tiempo.v1);
    this.chart.data.labels.push(tiempo.t1 + tiempo.t2);
    this.chart.data.datasets[this.nroMed].data.push(tiempo.v2);
    this.chart.data.labels.push(tiempo.t1 + tiempo.t2 + tiempo.t3);
    this.chart.data.datasets[this.nroMed].data.push(tiempo.v3);
    this.chart.data.labels.push(tiempo.t1 + tiempo.t2 + tiempo.t3 + tiempo.t4);
    this.chart.data.datasets[this.nroMed].data.push(tiempo.v4);
    this.chart.data.labels.push(tiempo.t1 + tiempo.t2 + tiempo.t3 + tiempo.t4 + tiempo.t5);
    this.chart.data.datasets[this.nroMed].data.push(tiempo.v5);
    // this.chart.data.datasets[0].data.push(response.data);
    this.chart.update();
    this.nroMed++;

  }

  constructor(private webSocketService: WebSocketService, private reglaService: ReglaService) {
    this.tiempos = new Array<Tiempos>();
  }

}
