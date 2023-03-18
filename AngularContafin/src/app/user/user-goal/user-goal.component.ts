import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { LoginService } from '../../login/login.service';
import { User } from '../../Interfaces/User/user.model';
import { stringToFileBuffer } from '@angular-devkit/core/src/virtual-fs/host';

@Component({
  selector: 'app-user-goal',
  templateUrl: './user-goal.component.html',
  styleUrls: ['./user-goal.component.css']
})
export class UserGoalComponent implements OnInit {

  public loggedUser;
  public imageView;
  public image: FormData;
  public noGoal: boolean;
  public addGoal: boolean;


  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
  }

  ngOnInit() {
    this.loggedUser = this.loginService.getLoggedUser();
    this.imageView = this.loginService.imageView;
  }

  //Other methods
  async updateUser() {//Cambiar servicio al que se llama
    this.loggedUser.dailyGoal = (+this.loggedUser.dailyGoal);
    await this.userService.updateUser(this.loggedUser.id, this.loggedUser)
      .then(
        (user:any) => {
          console.log("Usuario Actualizado: " + JSON.stringify(user));
          this.loginService.setLoggedUser(user);
          this.noGoal = false;
          this.addGoal = true;
        },
        error => {
          this.addGoal = false;
          this.noGoal = true;
        }
      )
  }

}
