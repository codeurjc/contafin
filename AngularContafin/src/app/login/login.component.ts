import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from './login.service';
import { User } from '../Interfaces/User/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent {

  constructor(private router: Router, private loginService: LoginService) { }

  @Input()
  private formkind: string;

  logIn(event: any, email: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(email, pass).subscribe(
      user => {
        console.log(user);
        this.router.navigate(['/home']);
      },
      error => alert('Invalid user or password')
    );
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

  checkIn(event: any,name: string, email: string, pass: string) {

    //Aqui te registras teniendo los parametros name email y pass

    event.preventDefault();

    this.loginService.logIn(email, pass).subscribe(
      user => {
        console.log(user);
        this.router.navigate(['/Home']);
      },
      error => alert('Invalid user or password')
    );
  }
}