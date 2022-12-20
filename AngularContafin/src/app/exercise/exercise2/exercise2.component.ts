import { Component, Input, Output, EventEmitter } from '@angular/core';
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

export class Exercise2Component {


  press: boolean = false;
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


  public statement: String;
  public texts: Array<String> = new Array();
  public answerText = "";
  public right: boolean;
  public result: any;
  public color = "exercise2";


  constructor(private http: HttpClient, private exerciseService: ExerciseService) {
    console.log(this.idExercise);
  }

  ngOnInit() {
    this.exerciseService.getExercise(this.idUnit, this.idLesson, this.idExercise)
      .then(
        (exercise : any) => {
          this.statement = exercise.statement;//Esto puede estar mal
          this.texts = exercise.texts;
        }
      )
  }

  async check() {
    this.result = {
      "result": this.answerText
    }
    await this.exerciseService.checkExercise(this.idUnit, this.idLesson, this.idExercise, this.result).then(
      (right : any) => {
      this.right = right.hasOwnProperty.arguments;
        if (this.right == true) {
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
