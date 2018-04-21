import {Component, Input} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../../environments/environment";

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise2',
  templateUrl: './exercise2.component.html'
})

export class Exercise2Component {


  @Input()
  idUnit: number;

  @Input()
  idLesson: number;

  @Input()
  idkind: number;

  @Input()
  idExercise: number;

  statement: String;

  constructor(private http: Http) {

  }
}
