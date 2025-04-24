import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../../../services/unit.service';
import { Unit } from '../../../Interfaces/Unit/unit.model';
import { User } from '../../../Interfaces/User/user.model';
import { LoginService } from '../../../services/login.service';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { TokenStorageService } from '../../../services/token-storage.service';
import { ErrorService } from '../../../services/error.service';
import { LessonsService } from '../../../services/lesson.service';


@Component({
    selector: 'body_home',
    templateUrl:
        './body_home.component.html'
})
export class BodyHomeComponent implements OnInit {
    closeResult: string;
    kind1 = '1';
    kind2 = '2';
    public units : Unit [] = [];
    public lessonsCompleted: number[];
    public lessonUnits = {};
    public lessonBo = {};
    public loggedUser: User;
    public isLoggedUser: boolean = false;
    public idUnitlast = 0;
    public n: number = 0;

    constructor(private modalService: NgbModal, private unitsService: UnitsService, private lessonService : LessonsService, private tokenStorage: TokenStorageService,private errorService : ErrorService) {
    }

    async ngOnInit() {
        if (this.tokenStorage.getToken()) {
            this.loggedUser = this.tokenStorage.getUser();
            this.isLoggedUser = true;
        }
        this.units = new Array();
        this.lessonsCompleted = new Array();
        this.idUnitlast = 0;

        await this.getUnits();
    
    }

    async getUnits() {
        await this.unitsService.getUnits()
            .then((units : any) => {
                this.units = units;
                  
                //Get the number of Lessons completed of all the units
            }).catch(error => this.errorService.handleError(error));

            await this.lessonService.UnitCompletedForHome().then((n : any) => {
                this.n = n.ncompleted;
                this.idUnitlast = n.unitId;
                console.log("numero lecciones : "+JSON.stringify(n));
                }).catch(error => this.errorService.handleError(error));
        
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