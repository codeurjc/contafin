import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ErrorService } from '../error/error.service';

@Injectable()
export class CanActivateUser implements CanActivate {

  constructor(private router: Router, private loginService: LoginService, private errorService: ErrorService) { }

  canActivate() {
    if (this.loginService.isLoggedUser()) {
      return true;
    }
    else {
      //Redirect to error
      this.errorService.setMessage("No tienes permiso para acceder a esta página");
      this.router.navigate(['/Error']);
      return false;
    }
  }

}
