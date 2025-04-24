import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { LoginService } from '../services/login.service';
import { ErrorService } from '../services/error.service';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable()
export class CanActivateAdmin implements CanActivate {

  constructor(private router: Router, private tokenStorage: TokenStorageService, private errorService: ErrorService) { }

  canActivate() {
    if (this.tokenStorage.getLoginInfo().isAdmin) {
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
