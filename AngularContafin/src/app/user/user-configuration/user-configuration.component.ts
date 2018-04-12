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
  private updatedUser: User = {
    id: 0,
    name: "",
    email: "",
    passwordHash: "",
    level: 1,
    points: 0,
    streak: 0,
    fluency: 0,
    lastConnection: "-",
    lastUnit: 0,
    lastLesson: 0,
    exp: 0,
    needexp: 10,
    roles: [],
  };

  constructor(private router: Router, private loginService: LoginService, private userService: UserService) {
    this.loggedUser = loginService.getLoggedUser();
  }

  ngOnInit() {
    this.userService.getUser(this.loggedUser.id);
  }

  //Other methods
  updateUser() {
    this.userService.updateUser(this.loggedUser.id, this.updatedUser)
      .subscribe(
        user => {
          this.loginService.setLoggedUser(user);
          this.router.navigate(['/User/Configuration']);
          this.noData = false;
          this.updateUser = null;
        },
        error => {
          this.noData = true;
        }
      )
  }

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
