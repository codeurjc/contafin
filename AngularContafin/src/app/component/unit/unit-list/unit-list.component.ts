import { Component, OnInit } from '@angular/core';
import { UnitsService } from '../../../services/unit.service';
import { User } from '../../../Interfaces/User/user.model';
import { last } from 'rxjs-compat/operator/last';
import { Unit } from '../../../Interfaces/Unit/unit.model';
import { ErrorService } from '../../../services/error.service';

@Component({
  selector: 'unit-list',
  templateUrl: './unit-list.component.html',
  styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit {

  spinner: boolean = false;
  units: Unit [];

  

  constructor(private unitService: UnitsService, private errorService :ErrorService) { }

  ngOnInit() {
    this.getUnits();
  }

  async getUnits() {
    this.spinner = true; 
    await this.unitService.getUnits()
      .then(
        (units : Unit[])=> {
          this.units = units;
        }
      ).catch((error) =>{
        this.errorService.handleError(error);
      });
    this.spinner = false;
  }

  public async deleteUnit(id){
    console.log("El id a eliminar es: " + id);
    this.spinner = true;
    await this.unitService.deleteUnit(id)
    .then(()=>{
      this.getUnits();
    }).catch((error) =>{
      this.errorService.handleError(error);
    });;

    this.spinner = false;
  }


}
