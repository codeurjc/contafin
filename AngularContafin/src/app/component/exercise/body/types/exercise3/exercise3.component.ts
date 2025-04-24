import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { ExerciseService } from '../../../../../services/exercise.service';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { Exercise } from '../../../../../Interfaces/Exercise/exercise.model';
import { Answer } from '../../../../../Interfaces/Answer/answer.model';
import { ErrorService } from '../../../../../services/error.service';
import { element } from 'protractor';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'exercise3',
  templateUrl: './exercise3.component.html'
})

export class Exercise3Component implements OnInit {

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
  newExercise = new EventEmitter<any>();

  public right: boolean = false;
  public statement: String = '';
  public result: Answer= null;
  public press:boolean = false;
  public img1 : SafeUrl = null;
  public img2 : SafeUrl= null;
  public img3 : SafeUrl= null;
  public columnLeftText;
  public columnRightText;
  public leftoptions = [];
  public rightoptions = [];
  public pairsAnswer = {};
  public pairs = {};
  public option = {};
  public completedNumber = 0;
  public disabled = {};
  public solution = new Array();

  exerciseForm : FormGroup;

  constructor(private exerciseService: ExerciseService,public formBuilder: FormBuilder, private sanitizer: DomSanitizer, private errorService : ErrorService) {
    console.log(this.exercise);
  }

  async ngOnInit() {
    this.exerciseForm = this.formBuilder.group({
        answerRight: new FormControl(""),
        answerLeft: new FormControl("")
    });


    await this.exerciseService.getExercise(this.exercise.id).then(
        (exercise : Exercise) => {
          this.statement = exercise.statement;//Esto puede estar mal
          this.columnLeftText = exercise.texts[0];
          this.columnRightText = exercise.texts[1];
          this.exercise = exercise;
        }
      ).catch(error => this.errorService.handleError(error));

      if(this.nElement-this.nTotal === 0){
        this.complete_bar = 0;
      }else{
        this.complete_bar = (this.nTotal-this.nElement)/this.nTotal*100;
      }

      if(this.columnRightText !== undefined && this.columnLeftText !== undefined && this.columnRightText !== null && this.columnLeftText !== null){
        await this.initPairs();
      }

    

  }


  public initPairs() {

    //Division parte izquierda y derecha
    this.leftoptions = this.columnLeftText.split(":").map((element: string) => {
      this.option[element.trim()] = "exercise1";
      return element.trim();
    });
    this.leftoptions = this.shuffleArray(this.leftoptions);
    console.log("Enunciados izquierda : " + JSON.stringify(this.leftoptions));
    this.rightoptions = this.columnRightText.split(":").map((element: string) => {
      this.option[element.trim()] = "exercise1";
      return element.trim();
    })

    this.rightoptions = this.shuffleArray(this.rightoptions);

    this.exercise.answer.result.split("/").forEach((element: string) => {
      let pair = element.split(":").map((element: string) => {
        return element.trim();
      });
      this.pairsAnswer[pair[0]] = pair[1];
      console.log("Par: " + JSON.stringify(pair));
    });




  }

  /*ngOnChanges(changes: SimpleChanges): void {
    if (changes.name) {
      this.setup();
    }
  }*/

  async check() {
    const FORM_CONTROL = this.exerciseForm.controls;
    let answerLeft = FORM_CONTROL.answerLeft.value;
    let answerRight = FORM_CONTROL.answerRight.value;
    await console.log("Left: " + answerLeft + " Right: " + answerRight);
    await console.log("Pairs: " + JSON.stringify(this.pairsAnswer[answerLeft]));
    if(answerLeft !== "" && answerRight !== ""){
      if(this.pairsAnswer[answerLeft] === answerRight){
        this.option[answerLeft] = "exercise1Good";
        this.option[answerRight] = "exercise1Good";
        (document.getElementById(answerLeft +"l") as HTMLInputElement).disabled = true;
        (document.getElementById(answerRight +"r") as HTMLInputElement).disabled = true;
        this.solution.push(answerLeft + " : " + answerRight);
        FORM_CONTROL.answerLeft.setValue("");
        FORM_CONTROL.answerRight.setValue("");
        this.completedNumber ++;
        this.points = this.points + 3;

        if(this.completedNumber === this.leftoptions.length){
          this.right = true;
          this.press = true;
        }

        //
      }else{
          this.points = this.points - 1;
          FORM_CONTROL.answerLeft.setValue("");
          FORM_CONTROL.answerRight.setValue("");
      }
    }  
  }


  public shuffleArray(array) {
    var m = array.length, t, i;
 
    while (m) {    
     i = Math.floor(Math.random() * m--);
     t = array[m];
     array[m] = array[i];
     array[i] = t;
    }
 
   return array;
  }

  nextExercise(){
    this.newExercise.next(this.right);
    this.press = false;
  }
}
