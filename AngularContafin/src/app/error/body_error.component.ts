import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login/login.service';
import { useAnimation } from '@angular/core/src/animation/dsl';
import { ErrorService } from '../error/error.service';

@Component({
    selector: 'body_error',
    templateUrl:
        './body_error.component.html'
})
export class BodyErrorComponent {
    [x: string]: any;
    closeResult: string;
    errorMessage: string;

    constructor(private modalService: NgbModal, public loginService: LoginService, private errorService: ErrorService) {
        this.loginService.isLoggedUser();
        this.loggedUser = loginService.getLoggedUser();
        this.setMessage();
    }

    setMessage() {
        this.errorMessage = this.errorService.getMessage();
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