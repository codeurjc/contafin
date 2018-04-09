import { Component } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavBarComponent  {

  public isAdmin: boolean;
  public isLoggedUser: boolean;

  constructor(public loginService: LoginService){
    this.isAdmin = this.loginService.isAdministrator();
    this.isLoggedUser = this.loginService.isLoggedUser();
  }


}