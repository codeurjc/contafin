import { Component, Input, OnInit } from '@angular/core';
import { LessonsService } from '../../../services/lesson.service';
import { Unit } from '../../../Interfaces/Unit/unit.model';
import { Lesson } from '../../../Interfaces/Lesson/lesson.model';
import { UnitsService } from '../../../services/unit.service';

@Component({
    selector: 'body_lesson',
    templateUrl:
        './body_lesson.component.html'
})
export class BodyLessonComponent implements OnInit {

    @Input()
    id: number;

    nlesson: number = 0;

    public unit : Unit = null;

    lessonsCompleted: boolean[] = new Array();

    constructor(public lessonService: LessonsService, public unitService: UnitsService) {
    }

    ngOnInit() {
        this.getLessons();
    }

    async getLessons() {
        await this.unitService.getUnit(this.id)
                .then((unit : Unit) => {
                    this.unit = unit;
                    console.log("Unidad loca ; " + JSON.stringify(this.unit));
                    this.unitService.numberOfCompletedLessons2(unit.id).then((nlesson : any) =>{
                        this.nlesson = nlesson
                    });
                        this.lessonService.isCompleted2(this.unit.id)
                        .then((response : boolean[]) => {
                                this.lessonsCompleted = response;
                            });
                    
                })
                .catch(error => console.error(error));
    }
}