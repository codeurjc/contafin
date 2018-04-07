import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  navbar_index = true;
  body_index = true;
  navbar_normal = false;
  body_home = false;
  title = 'app';
}
