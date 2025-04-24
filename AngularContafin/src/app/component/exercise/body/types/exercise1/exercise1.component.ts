import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { ExerciseService } from '../../../../../services/exercise.service';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { Exercise } from '../../../../../Interfaces/Exercise/exercise.model';
import { Answer } from '../../../../../Interfaces/Answer/answer.model';
import { ErrorService } from '../../../../../services/error.service';


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
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public statement: String = '';
  public texts: Array<String> = new Array('1', '2', '3');
  public result: Answer= null;
  public press:boolean = false;
  public img1 : SafeUrl = null;
  public img2 : SafeUrl= null;
  public img3 : SafeUrl= null;
  public answer: string ="";


  constructor(private exerciseService: ExerciseService, private sanitizer: DomSanitizer, private errorService : ErrorService) {
    console.log(this.exercise);
  }

  async ngOnInit() {
    await this.exerciseService.getExercise(this.exercise.id).then(
        (exercise : Exercise) => {
          this.statement = exercise.statement;//Esto puede estar mal
          this.texts = exercise.texts;
          let objectURL = 'data:image/jpeg;base64,' + this.exercise.image1;       
          this.img1 = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          objectURL = 'data:image/jpeg;base64,' + this.exercise.image2;       
          this.img2 = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          objectURL = 'data:image/jpeg;base64,' + this.exercise.image3;       
          this.img3 = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        }
      ).catch(error => this.errorService.handleError(error));

      if(this.nElement-this.nTotal === 0){
        this.complete_bar = 0;
      }else{
        this.complete_bar = (this.nTotal-this.nElement)/this.nTotal*100;
      }
  }

  /*ngOnChanges(changes: SimpleChanges): void {
    if (changes.name) {
      this.setup();
    }
  }*/

  async check() {
    this.result = {
      result: this.answer
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result)
      .then(
        (response : boolean) => {
          this.right = response;
          console.log("Valor solución response" + response.valueOf());
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
  }

  nextExercise(){
    this.newExercise.next(this.right);
    this.press = false;
  }
}
