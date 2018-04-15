import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { User } from '../../Interfaces/User/user.model';

@Component({
  selector: 'app-admin-user-data',
  templateUrl: './admin-user-data.component.html',
  styleUrls: ['./admin-user-data.component.css']
})
export class AdminUserDataComponent implements OnInit {

  private lastPage: boolean = false;
  private size: number = 10;
  private page: number = 0;
  private spinner: boolean = false;
  users: User[];

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.spinner = true;
    if (this.lastPage === false) {
      this.adminService.getUserData(this.page, this.size)
        .subscribe(
          users => {
            if (this.page == 0) {
              this.users = users.content;
            }
            else {
              for (let i in users.content) {
                this.users.push(users.content[i]);
              }
            }
            this.page += 1;
            this.lastPage = users.last;
          }
        )
    }
    this.spinner = false;
  }

}
