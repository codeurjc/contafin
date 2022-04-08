import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  @Input()
  nElement: number;

  @Output()
  newExercise = new EventEmitter<boolean>();

  public img1: string;
  public img2: string;
  public img3: string;

  public right: boolean;
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public answer: string;
  public statement: String;
  public texts: Array<String> = new Array();
  public result: any;
  public press:boolean = false;

  constructor(private http: HttpClient, private exerciseService: ExerciseService) {
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
      this.press=true;
      console.log(this.nElement);
  }

  nextExercise(){
    this.newExercise.next(this.right);
    this.press = false;
  }
}
