import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ExerciseService } from '../../services/exercise.service';

@Component({
    selector: 'completeLesson',
    templateUrl:
        './completeLesson.component.html'
})
export class CompleteLessonComponent {

    public idLesson: number;
    public idUnit: number;
    public points: number;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public exerciseService: ExerciseService) {
        let idUnit = activatedRoute.snapshot.params['id'];
        this.idUnit = parseInt(idUnit);
        let idLesson = activatedRoute.snapshot.params['idlesson'];
        this.idLesson = parseInt(idLesson);
        let points = activatedRoute.snapshot.params['points'];
        this.points = parseInt(points);
    }
}