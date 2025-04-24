import {Component, Input} from '@angular/core';

@Component({
  selector: 'body_exercise',
  templateUrl: './body_exercise.component.html'
})

export class BodyExerciseComponent {

  @Input()
  idunit: number;

  @Input()
  idlesson: number;

  @Input()
  idkind: number;

  @Input()
  idexercise: number;

}
