import { Component } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UnitsService } from '../unit/unit.service';
import { Unit } from '../Interfaces/Unit/unit.model';
import{ User } from '../Interfaces/User/user.model'
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
    lessonsCompleted: number[] = [];
    public loggedUser: User;

    constructor(private modalService: NgbModal,public loginService: LoginService, private unitsService: UnitsService) {
        this.loginService.isLoggedUser(); 
        this.loggedUser = loginService.getLoggedUser();  
    }
    ngOnInit() {
        this.getUnits();
    }

    getUnits(){
        this.unitsService.getUnits()
            .then(units => {
                this.units = units;
                console.log(units.length)
                for(var i= 0; i<units.length;i++){
                    this.unitsService.numberOfCompletedLessons(units[i].id).subscribe(
                        number => {
                            this.numberLessonsCompleted[i] = number;
                            console.log('hola' + number);
                        },
                        error=> console.log('Hay un error')
                        
                    )
                }
            })
            .catch(error => console.error(error));
    }

    isCompleted(id:number){
        this.unitsService.isCompleted(id).subscribe(
            unitIsCompleted => this.unitIsCompleted = unitIsCompleted,
            error => console.log(error)
        );
    }

    numberLessonsCompleted(id:number){
        this.unitsService.numberOfCompletedLessons(id).subscribe(
            lessonsCompleted => {
                this.lessonsCompleted = lessonsCompleted; 
                console.log(lessonsCompleted)},
            error => console.log(error)
        );
    }

    //Es lo mismo que está en la promesa
    /*calculateLessonCompletedByUnit(){
        this.getUnits();
        console.log(this.units.length);
        for(var i= 0; i<this.units.length;i++){
            this.unitsService.numberOfCompletedLessons(this.units[i].id).subscribe(
                number => {
                    this.numberLessonsCompleted[i] = number;
                    console.log(number);
                },
                error=> console.log('Hay un error')
                
            )
        }
    }*/

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