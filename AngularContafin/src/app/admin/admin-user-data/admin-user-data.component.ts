import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { User } from '../../Interfaces/User/user.model';
import { last } from 'rxjs-compat/operator/last';

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

  

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getUsers();
  }

  async getUsers() {
    console.log("pasa por getUsers");
    this.spinner = true; 
    await this.adminService.getUserData()
      .then(
        (users : any)=> {
          this.users = users;
        }
      )
    this.spinner = false;
  }

  async getUsersExcel() {
    this.spinner = true; 
    await this.adminService.exporData();
    this.spinner = false;
  }

}
