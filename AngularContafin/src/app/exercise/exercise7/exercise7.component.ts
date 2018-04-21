import { Component, Input, OnInit } from '@angular/core';
import { Http } from "@angular/http";
import { Observable } from "rxjs/Observable";
import { environment } from "../../../environments/environment";
import { ExerciseService } from '../exercise.service';

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise7',
  templateUrl: './exercise7.component.html'
})

export class Exercise7Component implements OnInit {

  @Input()
  idUnit: number;

  @Input()
  idLesson: number;

  @Input()
  idkind: number;

  @Input()
  idExercise: number;

  public answer: String;
  public statement: String;
  public texts: Array<String> = new Array();

  constructor(private http: Http, private exerciseService: ExerciseService) {

  }

  ngOnInit() {
    this.exerciseService.getExercise(this.idUnit, this.idLesson, this.idExercise)
      .subscribe(
        exercise => {
          this.statement = exercise.statement;
          this.texts = exercise.texts;
        }
      )
  }

  
}
