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
    id: number;

    nlesson: number = 0;

    public unit: Unit;
    private lessons: Array<Lesson> = new Array();

    lessonsCompleted: boolean[] = new Array();

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {
    }
    ngOnInit() {
        this.lessons = new Array();
        this.lessonsCompleted = new Array();
        this.defineUnit();
        this.getUnits(this.id);
    }
    getUnits(id: number) {
        this.lessonService.getLessonsOfUnit(this.id).subscribe(
            (unit : any) => {
                this.unit = unit
                this.unitService.numberOfCompletedLessons(unit.id).subscribe(
                    (nlesson : any) => this.nlesson = nlesson,
                    error => console.log(error)
                )
                this.lessonService.isCompleted2(this.unit.id)
                    .subscribe(
                        (response : any) => {
                            this.lessonsCompleted = response;
                        }
                    )
                this.unit.lessons[0].id = 1;
                this.unit.lessons[1].id = 2;
                this.unit.lessons[2].id = 3;
            },
            error => console.log(error)
        )
    }

    numberoflessons(id: number) {
        this.unitService.numberOfCompletedLessons(id).subscribe(
            (nlesson : any) => this.nlesson = nlesson,
            error => console.log(error)
        )
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