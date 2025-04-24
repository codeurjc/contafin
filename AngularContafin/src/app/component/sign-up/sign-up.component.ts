import { Component, OnInit } from '@angular/core';
import { SignUpService } from '../../services/sign-up.service';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  private registered: boolean = false;
  private closeResult: string;
  public userData: any = {
    name: "",
    email: "",
    pass: ""
  }

  public buttonPressed: boolean;

  constructor(private router: Router, private signUpService: SignUpService, private modalService: NgbModal, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.userData;
    if ((document.getElementById('signup_button') as HTMLInputElement) !== undefined &&
      (document.getElementById('signup_button') as HTMLInputElement) !== null) {
      (document.getElementById('signup_button') as HTMLInputElement).disabled = false;
    }
    this.buttonPressed = true;
  }

  async signup() {

    if ((document.getElementById('signup_button') as HTMLInputElement) !== undefined &&
      (document.getElementById('signup_button') as HTMLInputElement) !== null){
      (document.getElementById('signup_button') as HTMLInputElement).disabled = true;
    }
    await this.signUpService.signup(this.userData)
      .then(
        (userp : any) => {
          this.tokenStorage.saveToken(userp.accessToken);
          this.tokenStorage.saveLoginInfo(userp);

          console.log("Datos logIn: " + userp);
          console.log("Datos token: " + userp.accessToken);
          console.log("Datos Usuario: " + userp.user);
          this.router.navigate(['/home']);
      },
      ).catch(error =>{
        alert('Invalid email');
        if ((document.getElementById('signup_button') as HTMLInputElement) !== undefined&&
          (document.getElementById('signup_button') as HTMLInputElement) !== null) {
          (document.getElementById('signup_button') as HTMLInputElement).disabled = false;
        }
      }); 
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
