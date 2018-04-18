import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from "@angular/router";

import { UserService } from '../user.service';
import { LoginService } from '../../login/login.service';
import { User } from '../../Interfaces/User/user.model';


@Component({
  selector: 'app-user-configuration',
  templateUrl: './user-configuration.component.html',
  styleUrls: ['./user-configuration.component.css']
})


export class UserConfigurationComponent implements OnInit {

  @ViewChild('newPass') newPassInput: ElementRef;
  @ViewChild('oldPass') oldPassInput: ElementRef;

  public loggedUser: User;
  public image: FormData;
  public errorMessage: string = "";
  public alertDanger: boolean = false;
  public alertSuccess: boolean = false;
  public rightPass: boolean;
  private userData: any = {
    name: "",
    email: "",
    passwordHash: ""
  }

  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
    this.loggedUser = loginService.getLoggedUser();
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods
  updateUser() {
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.oldPassInput.nativeElement.value != null) {
      this.userService.validation(this.loggedUser.id, this.oldPassInput.nativeElement.value)
        .subscribe(
          validation => {
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
  loadChanges() {
    if (this.rightPass) {
      this.userService.updateUser(this.loggedUser.id, this.userData)
        .subscribe(
          user => {
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
  uploadImage() {
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.image) {
      this.userService.uploadImage(this.loggedUser.id, this.image)
        .subscribe(
          response => {
            this.loggedUser = this.loginService.getLoggedUser();
            this.loggedUser.imgURL = "https://localhost:8080/api/User/Photo?a" + (new Date()).getTime();
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

}
