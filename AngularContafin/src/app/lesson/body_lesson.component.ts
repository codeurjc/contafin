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

    public unit = null;

    lessonsCompleted: boolean[] = new Array();

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {
    }

    ngOnInit() {
        this.getLessons();
    }

    async getLessons() {
        await this.unitService.getUnit(this.id)
                .then((unit : any) => {
                    this.unit = unit;
                    this.unitService.numberOfCompletedLessons2(unit.id).then((nlesson : any) =>{
                        this.nlesson = nlesson
                    });

                    
                        this.lessonService.isCompleted2(this.unit.id)
                        .then((response : any) => {
                                this.lessonsCompleted = response;
                            });
                    
                })
                .catch(error => console.error(error));
    }
}