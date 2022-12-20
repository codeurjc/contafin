import { Component, Input, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login/login.service';
import { LessonsService } from '../lesson/lesson.service';
import { User } from '../Interfaces/User/user.model';
import { Router } from '@angular/router';
import { UserService } from '../user/user.service';

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

    [x: string]: any;
    closeResult: string;
    response = null;
    public loggedUser: User;

    constructor(private router: Router, private userService: UserService, private modalService: NgbModal, public loginService: LoginService, private lessonService: LessonsService) {
        this.loginService.isLoggedUser();
        this.loggedUser = loginService.getLoggedUser();

    }
    ngOnInit() {
    }

    async completeLesson() {
       await this.lessonService.completeLesson(this.idUnit, this.idLesson).subscribe(
            response=> {
                this.response = response;
                if (this.loginService.isLoggedUser()) {
                    this.userService.getUser(this.loggedUser.id)
                        .then(
                            (user:any) => {
                                console.log(user);
                                this.loginService.setLoggedUser(user);
                                this.resetConfiguration();
                                this.router.navigate(['/ContinueLesson']);
                            }
                        )

                }
                else{
                    this.router.navigate(['/ContinueLesson']);
                }
            },
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

    resetConfiguration() {
        const config = this.router.config
            .map((route) => Object.assign({}, route));
        this.router.resetConfig(config);

    }

}