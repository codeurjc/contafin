import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../../services/login.service';
import { User } from '../../../Interfaces/User/user.model';
import { TokenStorageService } from '../../../services/token-storage.service';

@Component({
    selector: 'body_continueLesson',
    templateUrl:
        './body_continueLesson.component.html'
})
export class BodyContinueLessonComponent {

    closeResult: string;
    public loggedUser;

    constructor(private modalService: NgbModal, private tokenStorage: TokenStorageService) {
    }

    ngOnInit() {
        this.loggedUser = this.tokenStorage.getUser();
    }

    //deleteAllCompletedExercises() --> Home

    open(content) {
        this.modalService.open(content).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }

    public getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return `with: ${reason}`;
        }
    }

}