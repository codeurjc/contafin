import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { environment } from "../../../../../../environments/environment";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { ExerciseService } from '../../../../../services/exercise.service';
import { Answer } from '../../../../../Interfaces/Answer/answer.model';
import { Exercise } from '../../../../../Interfaces/Exercise/exercise.model';
import { ErrorService } from '../../../../../services/error.service';

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise5',
  templateUrl: './exercise5.component.html'
})

export class Exercise5Component implements OnInit {

  press: boolean = false;
  @Input()
  idUnit: number;

  @Input()
  idLesson: number;

  @Input()
  idkind: number;

  @Input()
  exercise: Exercise;

  @Input()
  nElement: number;

  @Input()
  nTotal: number;

  @Input()
  points: number;

  @Input()
  isLogged: boolean;

  complete_bar;

  @Output()
  newExercise = new EventEmitter<boolean>();

  public right: boolean = false;
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public answer: string;
  public statement: string;
  public texts: Array<string> = new Array();
  public result: Answer;

  constructor(private exerciseService: ExerciseService, private errorService : ErrorService) {

  }

  async ngOnInit() {
    await this.exerciseService.getExercise(this.exercise.id)
      .then(
        (exercise : any) => {
          this.statement = exercise.statement;
          this.texts = exercise.texts;
        }
      ).catch(error => this.errorService.handleError(error));

      if(this.nElement-this.nTotal === 0){
        this.complete_bar = 0;
      }else{
        this.complete_bar = (this.nTotal-this.nElement)/this.nTotal*100;
      }
  }

  //Correct the exercise
  async check() {
    this.result = {
      result : this.answer
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result)
      .then(
        (response : boolean) => {
          this.right = response;
          if (response) {
            this.points = this.points + 3;
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
            this.points = this.points - 1;
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
        }
      ).catch(error => this.errorService.handleError(error));

      this.press=true;
      console.log(this.nElement);
  }

  nextExercise(){
    this.newExercise
    this.newExercise.next(this.right);
    this.press = false;
  }
}
