import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../login/login.service';

@Injectable()
export class CanActivateUser implements CanActivate {

  constructor(private router: Router, private loginService: LoginService) { }

  canActivate() {
    if (this.loginService.isLoggedUser()) {
      return true;
    }
    else {
      //Redirect to error
      return false;
    }
  }

}
