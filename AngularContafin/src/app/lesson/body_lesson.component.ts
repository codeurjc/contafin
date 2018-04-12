import { Component, Input } from '@angular/core';
import { LessonsService } from './lesson.service';

@Component({
    selector: 'body_lesson',
    templateUrl:
        './body_lesson.component.html'
})
export class BodyLessonComponent {

    @Input()
    private id: number;

    constructor(public lessonService: LessonsService){
    }
 }