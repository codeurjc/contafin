import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { ExerciseService } from './exercise.service';
import { LessonsService } from '../lesson/lesson.service';

@Component({
  selector: 'exercise',
  templateUrl:
    './exercise.component.html'
})
export class ExerciseComponent implements OnInit {

  idUnit: number;
  idLesson: number;
  idExercise: number;
  idExercises = [];
  kindExercises: number[] = new Array();
  nElement = 4;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public exerciseService: ExerciseService, public lessonsService: LessonsService) {
    let idUnit = activatedRoute.snapshot.params['id'];
    this.idUnit = parseInt(idUnit);
    let idLesson = activatedRoute.snapshot.params['idlesson'];
    this.idLesson = parseInt(idLesson);
    /*let idExercise = activatedRoute.snapshot.params['idexercise'];
    this.idExercise = parseInt(idExercise);*/
    console.log("Id exercises :" + this.idExercises);
  }

  ngOnInit() {
    this.getExercises();
  }

  async getExercises(){
    await this.lessonsService.getLesson(this.idLesson).then(
      (lesson : any)=>{
        lesson.exercises.forEach(element => {
          this.idExercises.push(element);
          this.kindExercises.push(element.kind);
        });
      }
    );
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
