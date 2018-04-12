import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'lesson',
    templateUrl:
        './lesson.component.html'
})
export class LessonComponent {

    id: number;

    constructor(activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        this.id =id;
    }
}