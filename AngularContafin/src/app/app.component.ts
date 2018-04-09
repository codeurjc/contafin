import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  useron = false;
  adminon = false;


  body_index = true;
  body_home = false;
  title = 'app';
}
