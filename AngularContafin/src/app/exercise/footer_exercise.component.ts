import {Component} from '@angular/core';

@Component({
  selector: 'footer_exercise',
  templateUrl: './footer_exercise.component.html'
})

export class FooterExerciseComponent {

  public exerciseList: number[] = [1,2,5,7,0];
  public corrected: boolean;

  public i: number;

  constructor(){
    this.i = 0;
  }


  correct(){

      this.corrected = true;

  }

  skipExercise(exerciseList: number[], i: number){

    exerciseList.push(exerciseList[i]);
    this.i=i+1;

  }

  nextExercise(i: number){

    this.i=i+1;

  }

}
