import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login/login.service';
import { useAnimation } from '@angular/core/src/animation/dsl';

@Component({
    selector: 'body_continueLesson',
    templateUrl:
        './body_continueLesson.component.html'
})
export class BodyContinueLessonComponent {
    [x: string]: any;
    closeResult: string;

    constructor(private modalService: NgbModal, public loginService: LoginService) {
        this.loginService.isLoggedUser();
        this.loggedUser = loginService.getLoggedUser();
    }

    //deleteAllCompletedExercise() --> Home

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