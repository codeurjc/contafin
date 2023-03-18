import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { environment } from "../../../environments/environment";
import { ExerciseService } from '../exercise.service';

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise7',
  templateUrl: './exercise7.component.html'
})

export class Exercise7Component implements OnInit {

  press: boolean = false;
  @Input()
  idUnit: number;

  @Input()
  idLesson: number;

  @Input()
  idkind: number;

  @Input()
  exercise: any;

  @Input()
  nElement: number;

  @Output()
  newExercise = new EventEmitter<boolean>();

  public right: boolean;
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public answer: String;
  public statement: String;
  public texts: Array<String> = new Array();
  public result: any;

  constructor(private http: HttpClient, private exerciseService: ExerciseService) {

  }

  ngOnInit() {
    this.exerciseService.getExercise(this.exercise.id)
      .then(
        (exercise : any) => {
          this.statement = exercise.statement;
          this.texts = exercise.texts;
        }
      )
  }

  //Correct the exercise
  async check() {
    this.result = {
      "result": this.answer
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result)
      .then(
        (response : any) => {
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
