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

  public right: boolean;
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public answer: String;
  public statement: String;
  public texts: Array<String> = new Array();
  public result: any;

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

  //Correct the exercise
  check() {
    this.result = {
      "result": this.answer
    }
    this.exerciseService.checkExercise(this.idUnit, this.idLesson, this.idExercise, this.result)
      .subscribe(
        response => {
          this.right = response;
          if (response) {
            if (this.answer == "uno") {
              this.option1 = "exercise1Good";
            }
            if (this.answer == "dos") {
              this.option2 = "exercise1Good";
            }
            if (this.answer == "tres") {
              this.option3 = "exercise1Good";
            }
          }
          else {
            if (this.answer == "uno") {
              this.option1 = "exercise1Bad";
            }
            if (this.answer == "dos") {
              this.option2 = "exercise1Bad";
            }
            if (this.answer == "tres") {
              this.option3 = "exercise1Bad";
            }

          }
        },
        error => console.error(error)
      )
  }

}
