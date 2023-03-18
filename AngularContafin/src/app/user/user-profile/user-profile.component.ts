import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Chart } from 'chart.js';

import { LoginService } from '../../login/login.service';
import { UserService } from '../user.service';
import { User } from '../../Interfaces/User/user.model';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html'
})

export class UserProfileComponent implements OnInit {

  public loggedUser: User;
  public chart: any;
  public imageView;

  constructor(private loginService: LoginService, private userService: UserService) {
    this.loggedUser = this.loginService.getLoggedUser();
    this.imageView = this.loginService.imageView;

  }

  ngOnInit() {
    this.userService.getProgress(this.loggedUser.id)
      .then((progress:any) => this.lineChart(progress));

  }

  //Other methods
  public lineChart(progress: number[]) {
    this.chart = new Chart('myProgress', {
      type: 'line',
      data: {
        labels: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
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