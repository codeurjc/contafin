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

  public loggedUser: User;
  public image: FormData;
  public noGoal: boolean;
  public addGoal: boolean;


  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
    this.loggedUser = loginService.getLoggedUser();
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods
  async updateUser() {
    this.loggedUser.dailyGoal = (+this.loggedUser.dailyGoal);
    await this.userService.updateUser(this.loggedUser.id, this.loggedUser)
      .then(
        (user:any) => {
          console.log("User Servicio:" , user.json());
          this.loginService.setLoggedUser(user[0]);
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
