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
  idunit: number;

  @Input()
  idlesson: number;

  @Input()
  idkind: number;

  @Input()
  idexercise: number;

  solution: String;
  statement: String;
  texts: String[];

  constructor(private http: Http) {

  }

  getStatement() {
    return this.http.get(BASE_URL + '/' + this.idunit + '/Lessons/' + this.idlesson + '/Exercise/' + this.idkind + this.idexercise)
      .map(response => {
        this.statement = response.json();
        return this.statement;
      })
      .catch(error => this.handleError(error));
  }

  getTexts() {
    return this.http.get(BASE_URL + '/' + this.idunit + '/Lessons/' + this.idlesson + '/Exercise/' + this.idkind + this.idexercise)
      .map(response => {
        this.texts = response.json();
        return this.texts;
      })
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
