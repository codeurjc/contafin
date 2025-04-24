import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from "@angular/router";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../../../services/user.service';
import { LoginService } from '../../../services/login.service';
import { User } from '../../../Interfaces/User/user.model';
import { NavBarComponent } from '../../navbar/navbar.component';
import { TokenStorageService } from '../../../services/token-storage.service';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { ErrorService } from '../../../services/error.service';


@Component({
  selector: 'app-user-configuration',
  templateUrl: './user-configuration.component.html',
  styleUrls: ['./user-configuration.component.css']
})


export class UserConfigurationComponent implements OnInit {

  public closeResult: string;

  @ViewChild('newPass') newPassInput: ElementRef;
  @ViewChild('oldPass') oldPassInput: ElementRef;

  public loggedUser : User = {
    id : 0,
    name: '',
    email: '',
    passwordHash: '',
    level: 0,
    points: 0,
    streak: 0,
    fluency: 0,
    dailyGoal: 0,
    lastConnection: null,
    lastUnit: 0,
    lastLesson: 0,
    progress: [],
    remainingGoals: 0,
    exp: 0,
    needexp: 0,
    image : null,
    roles : []
  };
  public imageView;
  public image = null;;
  public errorMessage: string = "";
  public alertDanger: boolean = false;
  public alertSuccess: boolean = false;
  public noAdmin: boolean;
  public rightPass: boolean;
  public userData = {
    id: 0,
    name: '',
    email: '',
    passwordHash: ''
  };

  constructor(private router: Router, private sanitizer: DomSanitizer, private userService: UserService, private modalService: NgbModal, private tokenStorage: TokenStorageService, private errorService : ErrorService) { }

 ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.loggedUser = this.tokenStorage.getUser();
      console.log("Ususarioooooo " + JSON.stringify(this.loggedUser));
      this.userData.name = this.loggedUser.name;
      this.userData.email = this.loggedUser.email;
    }
    if(this.tokenStorage.getLoginInfo() != null) {
      this.imageView = this.tokenStorage.getLoginInfo().imageView;
      this.noAdmin = !this.tokenStorage.getLoginInfo().isAdmin;
    }
   
  }

  //Other methods
  async updateUser() {
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.oldPassInput.nativeElement.value != null && this.oldPassInput.nativeElement.value != '') {
      let data = {
        name: this.userData.name,
        email: this.userData.email,
        oldpass: this.oldPassInput.nativeElement.value,
        pass: this.newPassInput.nativeElement.value,
        file: this.image
      };

      

      console.log("Datos que se envian: " + JSON.stringify(data));

      await this.userService.validation(this.loggedUser.id, data)
        .then(
          (user : any) => {
            if (user != null) {
              this.getPasswords();
              this.loadChanges(user);
              this.alertSuccess = true;
              this.ngOnInit();
            }
            else {
              this.errorMessage = "Contraseña incorrecta.";
              this.alertDanger = true;
            }
            this.image = null;
          }
      ).catch(error => this.errorService.handleError(error));
    } else{
      this.errorMessage = "Introduce tu contraseña actual.";
      this.alertDanger = true;
    }
  }

  getPasswords() {
    this.oldPassInput.nativeElement.value = '';
    this.newPassInput.nativeElement.value = '';
  }

  //If the current password is right, save the changes
  async loadChanges(user) {
    this.tokenStorage.saveToken(user.token);
    this.tokenStorage.saveLoginInfo(user);
  }

  //Load image
  selectFile(event) {
    const file = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      this.image = reader.result.toString().split('base64,')[1]
    };
    reader.readAsDataURL(file);
  }

  //Save image
  /*async uploadImage(){
    this.alertDanger = false;
    this.alertSuccess = false;
    if (this.image) {
      await this.userService.uploadImage(this.loggedUser.id, this.image)
        .then(
          (user) => {
            this.tokenStorage.saveUser(user);
            this.router.navigate(['/User/Configuration']);
          }
        );
      this.image = null;
      this.alertDanger = false;
    }
    else {
      this.errorMessage = "No has seleccionado ninguna imagen.";
      this.alertDanger = true;
    }
  }*/

  async deleteAccount() {
   await this.userService.deleteAccount(this.loggedUser.id)
      .then(
        response => {
          this.tokenStorage.signOut();
          this.router.navigate(['/']);
        }
      ).catch(error => this.errorService.handleError(error));

  }

  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  public getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
