import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Chart } from 'chart.js';

import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';
import { User } from '../../../Interfaces/User/user.model';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html'
})

export class UserProfileComponent implements OnInit {

  public loggedUser: User;
  public chart: any;
  public imageView;

  public labels = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];

  constructor(private userService: UserService, private tokenStorage: TokenStorageService, private errorService : ErrorService) {
    if (this.tokenStorage.getToken()) {
      this.loggedUser = this.tokenStorage.getUser();
      if(this.tokenStorage.getLoginInfo()!==undefined && this.tokenStorage.getLoginInfo()!==null && this.tokenStorage.getLoginInfo().imageView!==undefined && this.tokenStorage.getLoginInfo().imageView!==null){
        this.imageView = this.tokenStorage.getLoginInfo().imageView;
      }
    }

  }

 async  ngOnInit() {
   await this.userService.getProgress(this.loggedUser.id)
      .then((progress:any) => 
        this.lineChart(progress))
      .catch(error => this.errorService.handleError(error));;

  }

  //Other methods
  public lineChart(progress: number[]) {
    let dateToday = new Date();
    let day = new Array(new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date());
    day[5].setDate(dateToday.getDate() - 6);
    day[4].setDate(dateToday.getDate() - 5); 
    day[3].setDate(dateToday.getDate() - 4);
    day[2].setDate(dateToday.getDate() - 3);
    day[1].setDate(dateToday.getDate() - 2);
    day[0].setDate(dateToday.getDate() - 1);
    console.log("Las fechas son: " + day[5].getDay());
    this.chart = new Chart('myProgress', {
      type: 'line',
      data: {
        labels: [
                this.labels[day[5].getDay()], 
                this.labels[day[4].getDay()],
                this.labels[day[3].getDay()],
                this.labels[day[2].getDay()],
                this.labels[day[1].getDay()],
                this.labels[day[0].getDay()],
                this.labels[dateToday.getDay()]
              ],
        datasets: [{
          label: 'Lecciones',
          data: progress,
          lineTension: 0,
          backgroundColor: [
            'rgba(255, 159, 64, 0.6)',
          ],
          borderWidth: 2,
          borderColor: '#777',
          hoverBorderWidth: 3,
          hoverBorderColor: '#000'
        }]
      },
      options: {
        legend: {
          display: true,
          position: 'right',
          labels: {
            fontColor: '#000'
          }
        },
        layout: {
          padding: {
            left: 50,
            right: 0,
            bottom: 0,
            top: 0
          }
        },
        tooltips: {
          enabled: true
        },
        scales: {
          yAxes: [{
            ticks: {
              max: 7,
              min: 0
            }
          }]
        }

      }

    })
  }

}