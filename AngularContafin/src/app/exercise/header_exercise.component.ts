import {Component} from '@angular/core';
import {NgbProgressbarConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'header_exercise',
  templateUrl: './header_exercise.component.html',
  providers: [NgbProgressbarConfig]
})

export class HeaderExerciseComponent {

  public progress: number;

  constructor() {
    this.progress = 0;
  }

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
