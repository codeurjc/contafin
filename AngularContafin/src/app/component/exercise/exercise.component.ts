import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LessonsService } from '../../services/lesson.service';
import { Lesson } from '../../Interfaces/Lesson/lesson.model';
import { TokenStorageService } from '../../services/token-storage.service';
import { ErrorService } from '../../services/error.service';

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
  nElement = null;
  nTotal = null;
  points : number = 0;

  isLogged: boolean = false;

  constructor(activatedRoute: ActivatedRoute, public lessonsService: LessonsService, private tokenStorage: TokenStorageService, private errorService : ErrorService) {
    let idUnit = activatedRoute.snapshot.params['id'];
    this.idUnit = parseInt(idUnit);
    let idLesson = activatedRoute.snapshot.params['idlesson'];
    this.idLesson = parseInt(idLesson);
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
    }
    /*let idExercise = activatedRoute.snapshot.params['idexercise'];
    this.idExercise = parseInt(idExercise);*/
  }

  ngOnInit() {
    this.getExercises();
  }

  async getExercises(){
    await this.lessonsService.getLesson(this.idLesson).then(
      (lesson : Lesson)=>{
        lesson.exercises.forEach(element => {
          this.idExercises.push(element);
          this.kindExercises.push(element.kind);
        });
        this.nElement = lesson.exercises.length;
        this.nTotal = lesson.exercises.length;
        console.log("Element: " + this.nElement);
        console.log("Total: " + this.nTotal);
      }
    ).catch(error => this.errorService.handleError(error));
  }

  newExercisechange(newExercise) {
    console.log("Valor para recuento de ejercicios: " + newExercise);
    console.log("Valor para recuento de ejercicios: " + (newExercise === true));
    if (newExercise.valueOf() === true) {
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
    console.log("Cambio nElement" + this.nElement);
  }

  pulsar() {
    
    console.log(this.idUnit);
    console.log(this.idLesson);
    console.log(this.idExercise);
    console.log(this.idExercises);
    console.log(this.kindExercises);
  }

}
