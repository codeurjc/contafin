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

    public unit: Unit = null;
    private lessons: Array<Lesson> = new Array();

    lessonsCompleted: boolean[] = new Array();

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {
    }
    ngOnInit() {
        this.lessons = new Array();
        this.lessonsCompleted = new Array();
        //this.defineUnit();
        this.getLessons();
    }
    async getLessons() {
        await this.unitService.getUnit(this.id)
                .then((unit : any) => {
                    this.unit = unit;
                    this.unitService.numberOfCompletedLessons2(unit.id).then((nlesson : any) =>{
                        this.nlesson = nlesson
                    });

                    if(this.nlesson>0){
                        this.lessonService.isCompleted2(this.unit.id)
                        .then((response : any) => {
                                this.lessonsCompleted = response;
                            });
                    }
                })
                .catch(error => console.error(error));
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