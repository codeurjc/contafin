import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UnitsService } from '../unit.service';
import { Unit } from '../../Interfaces/Unit/unit.model';
import { Lesson } from '../../Interfaces/Lesson/lesson.model';
import { Exercise } from '../../Interfaces/Exercise/exercise.model';
import { Answer } from '../../Interfaces/Answer/answer.model';


@Component({
  selector: 'app-unit-creation',
  templateUrl: './unit-creation.component.html',
  styleUrls: ['./unit-creation.component.css']
})
export class UnitCreationComponent implements OnInit {

  private answers: Array<Answer> = new Array();
  private exercises: Array<Exercise> = new Array();
  private lessons: Array<Lesson> = new Array();
  private unit: Unit;

  constructor(private router: Router, private unitService: UnitsService) {
  }

  ngOnInit() {
    this.defineUnit();
  }

  //Other methods
  addUnit() {
    this.unitService.addUnit(this.unit)
      .subscribe(
        unit => {
          console.log(unit);
          this.router.navigate(['/Admin/Home']);
        },
        error => console.log(error)
      )
  }


  //Only create an empty unit
  defineUnit() {
    for (var i = 0; i < 12; i++) {
      //Add all answers
      this.answers.push(
        {
          result: "",
        }
      )
      //Add all exercises 
      this.exercises.push(
        {
          kind: (i % 4) + 1,
          statement: "",
          texts: ["", "", ""],
          answer: this.answers[i]
        }
      )
    }

    //Add lesson 1
    this.lessons.push(
      {
        name: "",
        exercises: this.exercises.slice(0, 4),
      }
    )

    //Add lesson 2
    this.lessons.push(
      {
        name: "",
        exercises: this.exercises.slice(4, 8),
      }
    )

    //Add lesson 3
    this.lessons.push(
      {
        name: "",
        exercises: this.exercises.slice(8),
      }
    )

    this.unit = {
      name: "",
      lessons: this.lessons,
    }

  }


}
