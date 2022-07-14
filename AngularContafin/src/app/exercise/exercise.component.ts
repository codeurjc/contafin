import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { ExerciseService } from './exercise.service';

@Component({
  selector: 'exercise',
  templateUrl:
    './exercise.component.html'
})
export class ExerciseComponent implements OnInit {

  idUnit: number;
  idLesson: number;
  idExercise: number;
  idExercises: number[] = new Array();
  kindExercises: number[] = new Array();
  nElement = 4;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public exerciseService: ExerciseService) {
    let idUnit = activatedRoute.snapshot.params['id'];
    this.idUnit = parseInt(idUnit);
    let idLesson = activatedRoute.snapshot.params['idlesson'];
    this.idLesson = parseInt(idLesson);
    /*let idExercise = activatedRoute.snapshot.params['idexercise'];
    this.idExercise = parseInt(idExercise);*/
    this.idExercise = (((this.idUnit - 1) * 3 + this.idLesson) - 1) * 4 + 1/*this.idExercise*/;
    this.idExercises.push(this.idExercise);
    this.idExercises.push(this.idExercise + 1);
    this.idExercises.push(this.idExercise + 2);
    this.idExercises.push(this.idExercise + 3);
  }

  ngOnInit() {
    this.getExercises();
  }

  getExercises(){
    this.exerciseService.getOneExercise(this.idUnit, this.idLesson, this.idExercise)
      .then(
        (exercise : any) =>{
          this.kindExercises.push(exercise.kind);
          this.exerciseService.getOneExercise(this.idUnit, this.idLesson, this.idExercise + 1)
            .then(
              (exercise : any) => {
                this.kindExercises.push(exercise.kind);
                this.exerciseService.getOneExercise(this.idUnit, this.idLesson, this.idExercise + 2)
                  .then(
                    (exercise : any) => {
                      this.kindExercises.push(exercise.kind);
                      this.exerciseService.getOneExercise(this.idUnit, this.idLesson, this.idExercise + 3)
                        .then(
                          (exercise : any) => {
                            this.kindExercises.push(exercise.kind);
                          }
                        )
                    }
                  )
              }
            )
        }
      )
      .catch(error => console.log(error))
  }

  newExercisechange(newExercise: boolean) {
    if (newExercise == true) {
      this.idExercises.shift();
      this.kindExercises.shift();
    }
    else {
      this.idExercises.push(this.idExercises[0]);
      this.kindExercises.push(this.kindExercises[0]);
      this.idExercises.shift();
      this.kindExercises.shift();
    }
    this.nElement = this.kindExercises.length;
  }

  pulsar() {
    console.log(this.idUnit);
    console.log(this.idLesson);
    console.log(this.idExercise);
    console.log(this.idExercises);
    console.log(this.kindExercises);
  }

}
