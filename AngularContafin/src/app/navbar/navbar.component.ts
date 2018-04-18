import { Component, OnInit } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from '../login/login.service';
import { User } from '../Interfaces/User/user.model';

import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavBarComponent implements OnInit {

  public isAdmin: boolean = false;
  public isLoggedUser: boolean = false;
  public loggedUser: User;

  constructor(private router: Router, public loginService: LoginService) { }

  ngOnInit() {
    this.isLoggedUser = this.loginService.isLoggedUser();
    this.isAdmin = this.loginService.isAdministrator();
    if (this.isLoggedUser){
      this.getUser();
    }
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

  getUser() {
    this.loggedUser = this.loginService.getLoggedUser();
    this.loggedUser.imgURL = "https://localhost:8080/api/User/Photo?a" + (new Date()).getTime();
  }

}