import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../unit/unit.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import { User } from '../Interfaces/User/user.model';
import { LoginService } from '../login/login.service';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';


@Component({
    selector: 'body_home',
    templateUrl:
        './body_home.component.html'
})
export class BodyHomeComponent implements OnInit {
    [x: string]: any;
    closeResult: string;
    kind1 = '1';
    kind2 = '2';
    units = [];
    lessonsCompleted: number[];
    public loggedUser: User;
    public isLoggedUser: boolean = false;

    constructor(private modalService: NgbModal, public loginService: LoginService, private unitsService: UnitsService) {
    }

    ngOnInit() {
        this.isLoggedUser = this.loginService.isLoggedUser();
        if (this.isLoggedUser){
            this.getUser();
        }
        this.units = new Array();
        this.lessonsCompleted = new Array();
        this.getUnits();
    }

    getUser() {
        this.loggedUser = this.loginService.getLoggedUser();
        console.log("Usuario : " + JSON.stringify(this.loggedUser));
    }

    async getUnits() {
        await this.unitsService.getUnits()
            .then((units : any) => {
                this.units = units;
                //Get the number of Lessons completed of all the units
                 this.units.forEach((element) =>{
                        this.unitsService.numberOfCompletedLessons2(element.id).then((n : any) => {
                        console.log(n);
                        this.lessonsCompleted.push(n);
                    });
                });
            })
            .catch(error => console.error(error));
            console.log(this.lessonsCompleted);
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