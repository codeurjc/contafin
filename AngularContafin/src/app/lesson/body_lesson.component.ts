import { Component, Input, OnInit } from '@angular/core';
import { LessonsService } from './lesson.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import { Lesson } from '../Interfaces/Lesson/lesson.model';
import { UnitsService } from '../unit/unit.service';

@Component({
    selector: 'body_lesson',
    templateUrl:
        './body_lesson.component.html'
})
export class BodyLessonComponent implements OnInit {

    @Input()
    private id: number;

    unit: Unit;
    nlesson: number = 0;

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {

    }

    ngOnInit() {
        this.getUnits(this.id);
    }

    getUnits(id: number) {
        this.lessonService.getLessonsOfUnit(this.id).subscribe(
            unit => {
                this.unit = unit
                this.unitService.numberOfCompletedLessons(unit.id).subscribe(
                    nlesson => this.nlesson = nlesson,
                    error => console.log(error)
                )
            },
            error => console.log(error)
        )
    }

    numberoflessons(id: number) {
        this.unitService.numberOfCompletedLessons(id).subscribe(
            nlesson => this.nlesson = nlesson,
            error => console.log(error)
        )
    }
    pulsar() {
        console.log(this.id);
        console.log(this.unit);
    }
}