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
    if (this.lastPage !== this.page) {
      await this.adminService.getUserData(this.page, this.size)
        .then(
          (users : any)=> {
            if (this.page == 0) {
              this.users = users.content;
            }
            else {
              for (let i in users.content) {
                this.users.push(users.content[i]);
              }
            }
            this.page += 1;
            this.lastPage = users.totalPages;
            console.log("Pagina: "+ this.page);
            console.log("Ultima agina: "+ this.lastPage);
          }
        )
    }
    this.spinner = false;
  }

}
