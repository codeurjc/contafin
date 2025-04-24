import { Component, OnInit } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from '../../services/login.service';
import { User } from '../../Interfaces/User/user.model';

import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavBarComponent implements OnInit {

  public isAdmin: boolean = false;
  public isLoggedUser: boolean = false;
  public loggedUser: User;
  public imageView;

  constructor(private router: Router, public loginService: LoginService, private tokenStorage: TokenStorageService) { 
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.loggedUser = this.tokenStorage.getUser();
      this.isLoggedUser = true;
      this.isAdmin = this.tokenStorage.getLoginInfo().isAdmin;
      if (this.isLoggedUser){
        this.getImage();
      }
    }
  }

  async logOut() {
    console.log("Logout");
    this.isLoggedUser = false;
    this.tokenStorage.signOut();
    console.log('Logged out');
    this.router.navigate(['/']);
  }

  getImage() {
    this.imageView = this.tokenStorage.getLoginInfo().imageView;
  }

}