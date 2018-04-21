import {Component, Input} from '@angular/core';
import {environment} from "../../../environments/environment";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise5',
  templateUrl: './exercise5.component.html'
})

export class Exercise5Component {

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
