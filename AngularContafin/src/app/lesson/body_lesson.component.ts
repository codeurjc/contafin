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

    nlesson: number = 0;

    public unit: Unit;
    private lessons: Array<Lesson> = new Array();

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {

    }

    ngOnInit() {
        this.defineUnit();
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

    //Only create an empty unit
    defineUnit() {
        //Add lesson 1
        this.lessons.push(
            {
                name: "",
                exercises: []
            }
        )
        //Add lesson 2
        this.lessons.push(
            {
                name: "",
                exercises: []
            }
        )
        //Add lesson 3
        this.lessons.push(
            {
                name: "",
                exercises: [],
            }
        )
        this.unit = {
            name: "",
            lessons: this.lessons,
        }
    }
}