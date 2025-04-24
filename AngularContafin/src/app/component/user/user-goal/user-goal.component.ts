import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../../services/user.service';
import { LoginService } from '../../../services/login.service';
import { User } from '../../../Interfaces/User/user.model';
import { stringToFileBuffer } from '@angular-devkit/core/src/virtual-fs/host';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';

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


  constructor(private userService: UserService, private tokenStorage: TokenStorageService, private errorService : ErrorService) {
  }

  async ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.loggedUser = this.tokenStorage.getUser();
      if(this.tokenStorage.getLoginInfo()!==undefined && this.tokenStorage.getLoginInfo()!==null && this.tokenStorage.getLoginInfo().imageView!==undefined && this.tokenStorage.getLoginInfo().imageView!==null){
        this.imageView = this.tokenStorage.getLoginInfo().imageView;
      }
    }

    
    
  }

  //Other methods
  async updateUser() {//Cambiar servicio al que se llama
    this.loggedUser.dailyGoal = (this.loggedUser.dailyGoal);
    await this.userService.updateUser(this.loggedUser.id, this.loggedUser)
      .then(
        (user:User) => {
          console.log("Usuario Actualizado: " + JSON.stringify(user));
          this.tokenStorage.saveUser(user);
          this.noGoal = false;
          this.addGoal = true;
        }
      ).catch(error => this.errorService.handleError(error));
  }

}
