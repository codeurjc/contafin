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
    units: Unit[];
    unitIsCompleted: Boolean[] = [];
    bool:boolean;

    constructor(private modalService: NgbModal,public loginService: LoginService, private unitsService: UnitsService) {
        this.loginService.isLoggedUser();
     }

    
    ngOnInit(){
        this.unitsService.getUnits().subscribe(
            units => this.units = units,
            error => console.log(error)
        );
    }
    /*pulsar(){
        console.log(this.units);
        for (let i = 0; i < this.units.length; i++) {
            this.unitIsCompleted.push(this.isCompleted(i));
            console.log(this.isCompleted(i));
        }
        console.log(this.unitIsCompleted);
    }*/

    isCompleted(id:number){
        this.unitsService.isCompleted(id).subscribe(
            bool => this.bool = bool,
            error => console.log(error)
        );
        return this.bool;
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