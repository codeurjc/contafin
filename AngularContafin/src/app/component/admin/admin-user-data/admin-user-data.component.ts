import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../services/admin.service';
import { User } from '../../../Interfaces/User/user.model';
import { last } from 'rxjs-compat/operator/last';
import { ErrorService } from '../../../services/error.service';

@Component({
  selector: 'app-admin-user-data',
  templateUrl: './admin-user-data.component.html',
  styleUrls: ['./admin-user-data.component.css']
})
export class AdminUserDataComponent implements OnInit {

  lastPage: number = -1;
  size: number = 10;
  page: number = 0;
  spinner: boolean = false;
  users: User[];

  

  constructor(private adminService: AdminService, private errorService : ErrorService) { }

  ngOnInit() {
    this.getUsers();
  }

  async getUsers() {
    console.log("pasa por getUsers");
    this.spinner = true; 
    await this.adminService.getUserData()
      .then(
        (users : User[])=> {
          this.users = users;
        }
      ).catch(error => this.errorService.handleError(error));
    this.spinner = false;
  }

  async getUsersExcel() {
    this.spinner = true; 
    await this.adminService.exporData()
    .catch(error => this.errorService.handleError(error));
    this.spinner = false;
  }

}
