import {Component} from '@angular/core';
import {NgbProgressbarConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'body_exercise',
  templateUrl: './body_exercise.component.html',
  providers: [NgbProgressbarConfig]
})

export class BodyExerciseComponent {

  img1 = "../assets/machine.jpg";
  img2 = "../assets/land.jpg";
  img3 = "../assets/truck.jpg";

  statement = "https://localhost:8080/api/Unit/1/Lesson/1/Exercise/1/statement";



}

export class NgbdProgressbarConfig {
  constructor(config: NgbProgressbarConfig) {
    // customize default values of progress bars used by this component tree
    config.max = 1000;
    config.striped = true;
    config.animated = true;
    config.type = 'info';
    config.height = '20px';
  }
}
