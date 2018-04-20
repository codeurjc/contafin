import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login/login.service';
import { useAnimation } from '@angular/core/src/animation/dsl';
import { LessonsService } from '../lesson/lesson.service';

@Component({
    selector: 'body_completeLesson',
    templateUrl:
        './body_completeLesson.component.html'
})
export class BodyCompleteLessonComponent {
    [x: string]: any;
    closeResult: string;
    response: boolean;

    constructor(private modalService: NgbModal, public loginService: LoginService, private lessonService: LessonsService, idUnit: number, idLesson: number) {
        this.loginService.isLoggedUser();
        this.loggedUser = loginService.getLoggedUser();
        this.completeLesson();
    }

    //deleteAllCompletedExercise() --> Home
    completeLesson() {
        this.lessonService.completeLesson(this.idUnit, this.idLesson).subscribe(
            response => this.response = response,
            error => console.log(error)
        );
    }
    open(content) {
        this.modalService.open(content).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }

    private getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return `with: ${reason}`;
        }
    }

}