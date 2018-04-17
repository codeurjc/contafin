import {Component} from '@angular/core';

@Component({
  selector: 'exercise5',
  templateUrl: './exercise5.component.html'
})

export class Exercise5Component {

  statement = "https://localhost:8080/api/Unit/1/Lesson/1/Exercise/5/statement";

  exercise1: String;
  exercise2: String;
  exercise3: String;

  text1: String;
  text2: String;
  text3: String;

}
