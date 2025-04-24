import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule, ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from '../../services/login.service';
import { User } from '../../Interfaces/User/user.model';
import { TokenStorageService } from '../../services/token-storage.service';
import { ErrorService } from '../../services/error.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent {

  constructor(private router: Router, private loginService: LoginService, private modalService: NgbModal, private tokenStorage: TokenStorageService,private errorService : ErrorService) { }

  public closeResult: string;

  @Input()
  formkind: string;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';


  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;

    }
    console.log(this.formkind);
    console.log("Mecanso mucho");
  }

  async logIn(event: any, email: string, pass: string) {

    event.preventDefault();

    await this.loginService.logIn(email, pass).then(
      (userp : any) => {
        
        this.tokenStorage.saveToken(userp.token);
        this.tokenStorage.saveLoginInfo(userp);

        this.isLoginFailed = false;
        this.isLoggedIn = true;


        console.log("Datos logIn: " + userp);
        console.log("Datos token: " + userp.token);
        console.log("Datos Usuario: " + userp.user);
        this.reloadPage();
        this.router.navigate(['/home']);
      },
    ).catch(error => this.errorService.handleError(error));
  }

  public reloadPage() {
    window.location.reload();
  }

  async logOut() {
    this.isLoggedIn = false;
    this.tokenStorage.signOut();
    console.log('Logged out');
    this.router.navigate(['/']);
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