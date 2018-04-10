import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../unit/unit.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import { LoginService } from '../login/login.service';

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
    a: object;

    constructor(private modalService: NgbModal,public loginService: LoginService, private unitsService: UnitsService) {
        this.loginService.isLoggedUser();
     }

    
    ngOnInit(){
        this.unitsService.getUnits().subscribe(
            units => this.units = units,
            error => console.log(error)
        );
        console.log(this.units);
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