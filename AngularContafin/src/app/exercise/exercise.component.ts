import { Component } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { ExerciseService } from './exercise.service';

@Component({
  selector: 'exercise',
  templateUrl:
    './exercise.component.html'
})
export class ExerciseComponent {

  idunit: number;
  idlesson: number;
  idexercise: number;
  exercises: Exercise[] = new Array();

  constructor(private router: Router, activatedRoute: ActivatedRoute, public exerciseService: ExerciseService) {
    let idunit = activatedRoute.snapshot.params['id'];
    this.idunit = parseInt(idunit);
    let idlesson = activatedRoute.snapshot.params['idlesson'];
    this.idlesson = parseInt(idlesson);
    let idexercise = activatedRoute.snapshot.params['idexercise'];
    this.idexercise = parseInt(idexercise);
    this.idlesson = (this.idunit-1)*3+this.idlesson;
    this.idexercise = (this.idlesson-1)*3+this.idexercise;
    this.getExercises(this.idunit,this.idlesson,this.idexercise);
    this.getExercises(this.idunit,this.idlesson,this.idexercise+1);
    this.getExercises(this.idunit,this.idlesson,this.idexercise+2);
    this.getExercises(this.idunit,this.idlesson,this.idexercise+3);
  }

  getExercises(idunit: number, idlesson: number, idexercise: number) {
    this.exerciseService.getExercise(idunit, idlesson, idexercise).subscribe(
      nlesson => this.exercises.push(nlesson),
      error => console.log(error)
    )
  }


  pulsar() {
    console.log(this.idunit);
    console.log(this.idlesson);
    console.log(this.idexercise);
    console.log(this.exercises);
  }

}
