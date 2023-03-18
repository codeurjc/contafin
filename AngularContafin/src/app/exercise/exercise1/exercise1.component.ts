import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { ExerciseService } from '../exercise.service';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';


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
  exercise: any;

  @Input()
  nElement: number;

  @Output()
  newExercise = new EventEmitter<boolean>();

  public img1: any;
  public img2: any;
  public img3: any;

  public right: boolean = false;
  public option1: string = "exercise1";
  public option2: string = "exercise1";
  public option3: string = "exercise1";
  public answer: string;
  public statement: String;
  public texts: Array<String> = new Array();
  public result: any;
  public press:boolean = false;

  constructor(private http: HttpClient, private exerciseService: ExerciseService, private sanitizer: DomSanitizer) {
    console.log(this.exercise);

  }
  ngOnInit() {
    console.log("Exercise 1")
    let objectURL = 'data:image/jpeg;base64,' + this.exercise.image1;       
    this.img1 = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    objectURL = 'data:image/jpeg;base64,' + this.exercise.image2;       
    this.img2 = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    objectURL = 'data:image/jpeg;base64,' + this.exercise.image3;       
    this.img3 = this.sanitizer.bypassSecurityTrustUrl(objectURL);

    this.exerciseService.getExercise(this.exercise.id)
      .then(
        (exercise :any) => {
          this.statement = exercise.statement;
          this.texts = exercise.texts;
        }
      )
  }

  async check() {
    this.result = {
      result: this.answer
    }
    await this.exerciseService.checkExercise(this.exercise.id, this.result)
      .then(
        (response :any) => {
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
