import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from "../../../environments/environment";
import { ExerciseService } from '../exercise.service';
import { Exercise } from '../../Interfaces/Exercise/exercise.model';

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
  exercise: any;

  @Input()
  nElement: number;

  @Input()
  nTotal: number;

  a = 0;

  @Output()
  newExercise = new EventEmitter<boolean>();


  public statement: String;
  public texts: Array<String> = new Array();
  public answerText = "";
  public right: boolean;
  public result: any;
  public color = "exercise2";


  constructor(private http: HttpClient, private exerciseService: ExerciseService) {
    console.log(this.exercise);
  }

  ngOnInit() {
    this.exerciseService.getExercise(this.exercise.id)
      .then(
        (exercise : any) => {
          this.statement = exercise.statement;//Esto puede estar mal
          this.texts = exercise.texts;
        }
      )

      if(this.nTotal-this.nElement === 0){
        this.a = 0;
      }else{
        this.a = (this.nTotal-this.nElement)/this.nTotal*100;
      }
  }

  async check() {
    this.result = {
      "result": this.answerText
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result).then(
      (right : any) => {
      this.right = right;
        if (this.right) {
          this.color = "exercise1Good";
        }
        else {
          this.color = "exercise1Bad";
        }

      }
    )
    this.press=true;
    console.log(this.nElement);
  }

  nextExercise(){
    this.newExercise.next(this.right);
    this.press = false;
  }
  
}
