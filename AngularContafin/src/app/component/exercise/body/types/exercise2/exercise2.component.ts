import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from "../../../../../../environments/environment";
import { ExerciseService } from '../../../../../services/exercise.service';
import { Exercise } from '../../../../../Interfaces/Exercise/exercise.model';
import { Answer } from '../../../../../Interfaces/Answer/answer.model';
import { ErrorService } from '../../../../../services/error.service';

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise2',
  templateUrl: './exercise2.component.html'
})

export class Exercise2Component implements OnInit {


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

  complete_bar = 0;

  @Output()
  newExercise = new EventEmitter<boolean>();


  public statement: String;
  public texts: Array<String> = new Array();
  public answerText : string = "";
  public right: boolean = false;
  public result: Answer;
  public color = "exercise2";
  


  constructor(private exerciseService: ExerciseService, private errorService : ErrorService) {
    console.log(this.exercise);
  }

  async ngOnInit() {
    await this.exerciseService.getExercise(this.exercise.id)
      .then(
        (exercise : Exercise) => {
          this.statement = exercise.statement;//Esto puede estar mal
          this.texts = exercise.texts;
        }
      ).catch(error => this.errorService.handleError(error));

      if(this.nTotal-this.nElement === 0){
        this.complete_bar = 0;
      }else{
        this.complete_bar = (this.nTotal-this.nElement)/this.nTotal*100;
      }
  }

  async check() {
    this.result = {
      result : this.answerText
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result).then(
      (right : boolean) => {
      this.right = right;
        if (this.right) {
          this.color = "exercise1Good";
          this.points = this.points + 3;
        }
        else {
          this.color = "exercise1Bad";
          this.points = this.points - 1;
        }

      }
    ).catch(error => this.errorService.handleError(error));
    this.press=true;
    console.log(this.nElement);
  }

  nextExercise(){
    this.newExercise.next(this.right);
    this.press = false;
  }
  
}
