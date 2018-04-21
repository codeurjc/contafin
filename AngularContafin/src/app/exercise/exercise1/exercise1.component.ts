import { Component, Input, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import { ExerciseService } from '../exercise.service';


@Component({
  selector: 'exercise1',
  templateUrl: './exercise1.component.html'
})

export class Exercise1Component implements OnInit {

  @Input()
  idUnit: number;

  @Input()
  idLesson: number;

  @Input()
  idkind: number;

  @Input()
  idExercise: number;

  public img1: string;
  public img2: string;
  public img3: string;

  public answer: String;
  public statement: String;
  public texts: Array<String> = new Array();

  constructor(private http: Http, private exerciseService: ExerciseService) {
    console.log(this.idExercise);

  }
  ngOnInit() {
    this.img1 = "https://localhost:8080/api/Unit/Exercise/" + this.idExercise + "/1";
    this.img2 = "https://localhost:8080/api/Unit/Exercise/" + this.idExercise + "/2";
    this.img3 = "https://localhost:8080/api/Unit/Exercise/" + this.idExercise + "/3";
    this.exerciseService.getExercise(this.idUnit, this.idLesson, this.idExercise)
      .subscribe(
        exercise => {
          this.statement = exercise.statement;
          this.texts = exercise.texts;
        }
      )
  }

}
