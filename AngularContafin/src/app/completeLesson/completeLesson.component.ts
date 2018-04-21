import { Component, Input } from '@angular/core';

@Component({
    selector: 'completeLesson',
    templateUrl:
        './completeLesson.component.html'
})
export class CompleteLessonComponent {

   @Input()
  idUnit: number;

  @Input()
  idLesson: number;
}