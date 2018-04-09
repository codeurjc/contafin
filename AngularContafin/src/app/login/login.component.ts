import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from './login.service';
import { User } from '../Interfaces/User/user.model';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html'
})

export class LoginComponent{

    constructor(private loginService: LoginService) { }

    logIn(event: any, email: string, pass: string) {

        event.preventDefault();
    
        this.loginService.logIn(email, pass).subscribe(
          u => console.log(u),
          error => alert('Invalid user or password')
        );
      }
    
      logOut() {
        this.loginService.logOut().subscribe(
          response => { },
          error => console.log('Error when trying to log out: ' + error)
        );
      }
}