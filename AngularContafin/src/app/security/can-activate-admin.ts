import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ErrorService } from '../error/error.service';

@Injectable()
export class CanActivateAdmin implements CanActivate {

  constructor(private router: Router, private loginService: LoginService, private errorService: ErrorService) { }

  canActivate() {
    if (this.loginService.isAdministrator()) {
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
