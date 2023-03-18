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
  public imageView;

  constructor(private router: Router, public loginService: LoginService) { 
  }

  ngOnInit() {
    this.isLoggedUser = this.loginService.isLoggedUser();
    this.isAdmin = this.loginService.isAdministrator();
    if (this.isLoggedUser){
      this.getImage();
    }
  }

  async logOut() {
    console.log("Logout");
    await this.loginService.logOut().then(
      (response:any) => {
        console.log('Logged out');
        this.router.navigate(['/']);
      },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

  getImage() {
    this.loggedUser = this.loginService.getLoggedUser();
    this.imageView = this.loginService.imageView;
  }

}