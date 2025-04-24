import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../../services/login.service';
import { ErrorService } from '../../../services/error.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { User } from '../../../Interfaces/User/user.model';

@Component({
    selector: 'body_error',
    templateUrl:
        './body_error.component.html'
})
export class BodyErrorComponent {
    closeResult: string;
    errorMessage: string;
    loggedUser : User;

    constructor(private modalService: NgbModal, private errorService: ErrorService, private tokenStorage: TokenStorageService) {
        if (this.tokenStorage.getToken()) {
            this.loggedUser = this.tokenStorage.getUser();
        }
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