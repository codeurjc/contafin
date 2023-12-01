import { Component, OnInit } from '@angular/core';
import { UnitsService } from '../unit.service';
import { User } from '../../Interfaces/User/user.model';
import { last } from 'rxjs-compat/operator/last';

@Component({
  selector: 'unit-list',
  templateUrl: './unit-list.component.html',
  styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit {

  spinner: boolean = false;
  units: [];

  

  constructor(private unitService: UnitsService) { }

  ngOnInit() {
    this.getUnits();
  }

  async getUnits() {
    this.spinner = true; 
    await this.unitService.getUnits()
      .then(
        (units : any)=> {
          this.units = units;
        }
      )
    this.spinner = false;
  }

  public async deleteUnit(id){
    console.log("El id a eliminar es: " + id);
    this.spinner = true;
    await this.unitService.deleteUnit(id)
    .then((data)=>{
      this.getUnits();
    });

    this.spinner = false;
  }


}
