import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Http } from "@angular/http";
import { Observable } from "rxjs/Observable";
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


  constructor(private http: Http, private exerciseService: ExerciseService) {
    console.log(this.idExercise);
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

  check() {
    this.result = {
      "result": this.answerText
    }
    this.exerciseService.checkExercise(this.idUnit, this.idLesson, this.idExercise, this.result).subscribe(
      right => {
      this.right = right;
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
