import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'exercise',
    templateUrl:
        './exercise.component.html'
})
export class ExerciseComponent {

  idunit: number;
  idlesson: number;
  idkind: number;
  idexercise: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute){

    let idunit = activatedRoute.snapshot.params['id'];
    this.idunit = idunit;
    let idlesson = activatedRoute.snapshot.params['idlesson'];
    this.idlesson = idlesson;
    let idkind = activatedRoute.snapshot.params['idkind'];
    this.idkind = idkind;
    let idexercise = activatedRoute.snapshot.params['idexercise'];
    this.idexercise = idexercise;

  }

 }
