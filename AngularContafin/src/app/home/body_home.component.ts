import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../unit/unit.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import { LoginService } from '../login/login.service';
import { useAnimation } from '@angular/core/src/animation/dsl';

@Component({
    selector: 'body_home',
    templateUrl:
        './body_home.component.html'
})
export class BodyHomeComponent {
    [x: string]: any;
    closeResult: string;
    kind1 = '1';
    kind2 = '2';
    units: Unit[] = [];
    unitIsCompleted: boolean;
    unitsCompleted: boolean[] = [];

    constructor(private modalService: NgbModal,public loginService: LoginService, private unitsService: UnitsService) {
        this.loginService.isLoggedUser();
        this.getUnits();
    }

    getUnits(){
        this.unitsService.getUnits().subscribe(
            units => this.units = units,
            error => console.log(error)
        )
    }

    isCompleted(id:number){
        this.unitsService.isCompleted(id).subscribe(
            unitIsCompleted => this.unitIsCompleted = unitIsCompleted,
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