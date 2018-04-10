import { Component } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from '../login/login.service';
import { User } from '../Interfaces/User/user.model';

import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavBarComponent {

  public isAdmin: boolean = false;
  public isLoggedUser: boolean = false;
  public loggedUser: User;

  constructor(private router: Router, public loginService: LoginService) {
    this.loggedUser = this.loginService.getLoggedUser();
    this.isLoggedUser = this.loginService.isLoggedUser();
    this.isAdmin = loginService.isAdministrator();
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => {
        console.log('Logged out');
        this.router.navigate(['/']);
      },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

}