import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { LoginService } from '../../login/login.service';
import { User } from '../../Interfaces/User/user.model';

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
  updatedUser: User = {
    id: 0,
    name: "",
    email: "",
    passwordHash: "",
    level: 1,
    points: 0,
    streak: 0,
    fluency: 0,
    dailyGoal: 0,
    lastConnection: "-",
    lastUnit: 0,
    lastLesson: 0,
    exp: 0,
    needexp: 10,
    roles: [],
  };

  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
    this.loggedUser = loginService.getLoggedUser();
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods
  updateUser() {
    this.userService.updateUser(this.loggedUser.id, this.updatedUser)
      .subscribe(
        user => {
          this.loginService.setLoggedUser(user[0]);
          this.router.navigate(['/User/Goal']);
          this.noGoal = false;
          this.addGoal = true;
          this.updateUser = null;
        },
        error => {
          this.addGoal = false;
          this.noGoal = true;
        }
      )
  }

}
