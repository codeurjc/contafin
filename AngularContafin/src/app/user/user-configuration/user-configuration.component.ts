import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from "@angular/router";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../user.service';
import { LoginService } from '../../login/login.service';
import { User } from '../../Interfaces/User/user.model';


@Component({
  selector: 'app-user-configuration',
  templateUrl: './user-configuration.component.html',
  styleUrls: ['./user-configuration.component.css']
})


export class UserConfigurationComponent implements OnInit {

  private closeResult: string;

  @ViewChild('newPass') newPassInput: ElementRef;
  @ViewChild('oldPass') oldPassInput: ElementRef;

  public loggedUser;
  public image: FormData;
  public errorMessage: string = "";
  public alertDanger: boolean = false;
  public alertSuccess: boolean = false;
  public noAdmin: boolean;
  public rightPass: boolean;
  userData: any = {
    name: "",
    email: "",
    passwordHash: ""
  }

  constructor(private router: Router, private loginService: LoginService, private userService: UserService, private modalService: NgbModal) { }

  ngOnInit() {
    this.loggedUser = this.loginService.getLoggedUser();
    this.noAdmin = !this.loginService.isAdministrator();
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods
  async updateUser() {
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.oldPassInput.nativeElement.value != null) {
      await this.userService.validation(this.loggedUser.id, this.oldPassInput.nativeElement.value)
        .then(
          (validation : any) => {
            this.rightPass = validation;
            console.log(this.rightPass);
            this.getPasswords();
            this.loadChanges();
          },
          error => console.log(error),
      );
    }
  }

  getPasswords() {
    this.userData.passwordHash = this.newPassInput.nativeElement.value;
    this.oldPassInput.nativeElement.value = '';
    this.newPassInput.nativeElement.value = ''
  }

  //If the current password is right, save the changes
  async loadChanges() {
    if (this.rightPass) {
      this.loggedUser.name = this.userData.name;
      await this.userService.updateUser(this.loggedUser.id, this.loggedUser)
        .then(
          (user: any) => {
            this.loggedUser.name = user.name;
            this.loggedUser.email = user.email;
            this.loggedUser.passwordHash = user.passwordHash;
            this.loggedUser = this.loginService.getLoggedUser();
            this.alertSuccess = true;
            this.router.navigate(['/User/Configuration']);
          },
          error => {
            this.errorMessage = "No has introducido ningún cambio.";
            this.alertDanger = true;
          }
        )
    }
    else {
      this.errorMessage = "Contraseña incorrecta.";
      this.alertDanger = true;
    }
    this.userData = {
      name: "",
      email: "",
      passwordHash: "",

    }
  }

  //Load image
  selectFile(event) {
    const file = event.target.files;
    this.image = new FormData();
    this.image.append('file', file[0]);
  }

  //Save image
  async uploadImage() {
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.image) {
      await this.userService.uploadImage(this.loggedUser.id, this.image)
        .then(
          (response : any) => {
            this.loggedUser = this.loginService.getLoggedUser();
            this.router.navigate(['/User/Configuration']);
          },
          error => console.error('Error al subir la imagen:' + error)
        );
      this.image = null;
      this.alertDanger = false;
    }
    else {
      this.errorMessage = "No has seleccionado ninguna imagen.";
      this.alertDanger = true;
    }
  }

  deleteAccount() {
    this.userService.deleteAccount(this.loggedUser.id)
      .subscribe(
        response => {
          this.loginService.isLogged = false;
          this.loginService.isAdmin = false;
          this.router.navigate(['/']);
        },
        error => console.log("Error al borrar el usuario")
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
