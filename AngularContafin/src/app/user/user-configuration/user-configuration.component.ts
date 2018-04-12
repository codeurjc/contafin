import { Component, OnInit } from '@angular/core';
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


  public loggedUser: User;
  public image: FormData;
  public noImage: boolean;
  public noData: boolean;


  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
    this.loggedUser = loginService.getLoggedUser();
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods

  selectFile(event) {
    const file = event.target.files;
    this.image = new FormData();
    this.image.append('file', file[0]);
  }

  uploadImage() {
    if (this.image) {
      this.userService.uploadImage(this.loggedUser.id, this.image)
        .subscribe(
          response => {
          this.router.navigate(['/User/Configuration']);
          },
          error => console.error('Error al subir la imagen:' + error)
        );
      this.image = null;
      this.noImage = false;
    }
    else {
      this.noImage = true;
    }
  }

}
