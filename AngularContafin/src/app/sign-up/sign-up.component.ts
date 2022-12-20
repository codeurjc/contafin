import { Component, OnInit } from '@angular/core';
import { User } from '../Interfaces/User/user.model';
import { SignUpService } from './sign-up.service';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  private registered: boolean = false;
  private closeResult: string;
  private userData: any = {
    name: "",
    email: "",
    pass: ""
  }

  constructor(private router: Router, private loginService: LoginService, private signUpService: SignUpService, private modalService: NgbModal) { }

  ngOnInit() {
    this.userData;
  }

  async signup() {
    await this.signUpService.signup(this.userData)
      .subscribe(
        user => {
          console.log(user);
          this.loginService.logIn(this.userData.email, this.userData.pass)
            .then(
              (user:any) => {
                this.router.navigate(['/']).then(
                  (response : any) => {
                    this.registered = true;
                    this.router.navigate(['/home'])
                  }
                );
              },
              error => alert('Algo ha salido mal')
            );
        },
        error => {
          console.log(error);
          alert('Usuario no registrado. Revisa los datos introducidos.');
        }
      )
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
