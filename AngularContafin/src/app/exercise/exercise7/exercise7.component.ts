import {Component} from '@angular/core';

@Component({
  selector: 'exercise7',
  templateUrl: './exercise7.component.html'
})

export class Exercise7Component {

  statement = "https://localhost:8080/api/Unit/1/Lesson/1/Exercise/7/statement";

  exercise1: String;
  exercise2: String;
  exercise3: String;

  text1: String;
  text2: String;
  text3: String;

}
