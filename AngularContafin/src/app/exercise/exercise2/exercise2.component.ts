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
  idunit: number;

  @Input()
  idlesson: number;

  @Input()
  idkind: number;

  @Input()
  idexercise: number;

  statement: String;

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

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
