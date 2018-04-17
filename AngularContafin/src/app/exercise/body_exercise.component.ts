import {Component} from '@angular/core';

@Component({
  selector: 'body_exercise',
  templateUrl: './body_exercise.component.html'
})

export class BodyExerciseComponent {

  img1 = "../assets/machine.jpg";
  img2 = "../assets/land.jpg";
  img3 = "../assets/truck.jpg";

  statement = "https://localhost:8080/api/Unit/1/Lesson/1/Exercise/1/statement";

}
