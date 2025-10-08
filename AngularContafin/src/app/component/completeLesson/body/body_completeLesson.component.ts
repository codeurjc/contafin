import { Component, Input, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../../services/login.service';
import { LessonsService } from '../../../services/lesson.service';
import { User } from '../../../Interfaces/User/user.model';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
@Component({
    selector: 'body_completeLesson',
    templateUrl:
        './body_completeLesson.component.html'
})
export class BodyCompleteLessonComponent implements OnInit {

    @Input()
    idUnit: number;

    @Input()
    idLesson: number;

    @Input()
    points: number;


    closeResult: string;

    public pointsLogged = "";

    public loggedUser: User;

    constructor(private router: Router, private userService: UserService, private modalService: NgbModal, private tokenStorage: TokenStorageService, private errorService : ErrorService) {
    }
    ngOnInit() {
        if (this.tokenStorage.getToken()) {
            this.loggedUser = this.tokenStorage.getUser();

            if(this.points !== null){
                this.pointsLogged = "+" + this.points + " Puntos";
            }else{
                this.pointsLogged = "";
            }
        }
    }

    async completeLesson() {
        if (this.loggedUser !== null && this.loggedUser !== undefined) {
            await this.userService.completeLesson(this.idLesson, this.points).then(
                (user : User)=> {
                    console.log("User Completed return: " + JSON.stringify(user));
                    this.tokenStorage.saveUser(user);
                    this.resetConfiguration();
                    this.router.navigate(['/ContinueLesson']);
                 }
            ).catch(error => this.errorService.handleError(error));
        }else{
            this.router.navigate(['/home']);
        }
       
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

    resetConfiguration() {
        const config = this.router.config
            .map((route) => Object.assign({}, route));
        this.router.resetConfig(config);

    }

}