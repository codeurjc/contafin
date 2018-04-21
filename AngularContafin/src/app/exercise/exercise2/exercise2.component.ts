import {Component, Input} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../../environments/environment";
import { ExerciseService } from '../exercise.service';
import { Exercise } from '../../Interfaces/Exercise/exercise.model';

const BASE_URL = environment.apiBase + '/Unit';

@Component({
  selector: 'exercise2',
  templateUrl: './exercise2.component.html'
})

export class Exercise2Component {


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
  public answerText: String;
  
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
  //Get the user's solution
  updateSolution(event) {
    this.answer = event.target.value;
  }
  pulsar(){
    console.log(this.answerText);
  }
}
