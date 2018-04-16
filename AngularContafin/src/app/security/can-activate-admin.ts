import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { LoginService } from '../login/login.service';

@Injectable()
export class CanActivateAdmin implements CanActivate {

  constructor(private router: Router, private loginService: LoginService) { }

  canActivate() {
    if (this.loginService.isAdministrator()) {
      return true;
    }
    else {
      //Redirect to error
      return false;
    }
  }

}
