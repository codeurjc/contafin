import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule, ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from './login.service';
import { User } from '../Interfaces/User/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent {

  constructor(private router: Router, private loginService: LoginService, private modalService: NgbModal) { }

  private closeResult: string;

  @Input()
  formkind: string;

  ngOnInit() {
    console.log(this.formkind);
  }

  async logIn(event: any, email: string, pass: string) {

    event.preventDefault();

    await this.loginService.logIn(email, pass).then(
      (user : any) => {
        console.log(user);
        if (this.formkind == "1") {
          this.router.navigate(['/home']);
        }
        else {
          this.router.navigate(['/']).then(
            (response: any) => {
              this.router.navigate(['/home'])
            }
          );
        }
      },
      error => alert('Invalid user or password')
    );
  }

  async logOut() {
    await this.loginService.logOut().then(
      (response: any) => {
        console.log('Logged out');
        this.router.navigate(['/']);
      },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}