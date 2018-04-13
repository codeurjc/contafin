import { Component, Input } from '@angular/core';
import { LessonsService } from './lesson.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import { Lesson } from '../Interfaces/Lesson/lesson.model';

@Component({
    selector: 'body_lesson',
    templateUrl:
        './body_lesson.component.html'
})
export class BodyLessonComponent {

    @Input()
    private id: number;

    unit:Unit;
    lesson1:Lesson;
    lesson2:Lesson;
    lesson3:Lesson;

    constructor(public lessonService: LessonsService){

    }

    OnInit(){
        this.getUnits(this.id);
    }

    getUnits(id: number){
        this.lessonService.getLessonsOfUnit(id).subscribe(
            unit => this.unit = unit,
            error => console.log(error)
        )
    }
    pulsar(){
        
    }
 }