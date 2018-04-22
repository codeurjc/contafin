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

  public alertDanger: boolean;
  public images: Array<FormData> = new Array();
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
          this.unit = unit;
          this.alertDanger = false;
          this.saveImages();
          this.router.navigate(['/Admin/Home']);
        },
        error => {
          console.log(error),
            this.alertDanger = true;
        }
      )
  }


  //Only create an empty unit
  defineUnit() {
    var aux = 0;
    for (var i = 0; i < 12; i++) {
      //Add all answers
      this.answers.push(
        {
          result: "",
        }
      )
      if ((i % 4) + 1 == 3) {
        aux = 5;
      }
      else if ((i % 4) + 1 == 4) {
        aux = 7;
      }
      else {
        aux = (i % 4) + 1;
      }
      //Add all exercises 
      this.exercises.push(
        {
          kind: aux,
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

  //Load images
  selectFile(event, i: number) {
    const file = event.target.files;
    this.images[i] = new FormData();
    this.images[i].append('file', file[0]);
    console.log("holaaaa " + i)
    console.log(file)
  }

  //Save images
  saveImages() {
    if (this.images) {
      this.uploadImages(0);
      this.uploadImages(1);
      this.uploadImages(2);
    }
    else {
      console.log('Array img vacio');
    }
  }

  //Upload the images of an exercise
  uploadImages(i: number) {
    let aux = 0;
    if (i != 0) {
      aux = 3 + (3 * (i - 1))
    }
    this.unitService.uploadImages(this.unit.lessons[i].exercises[0].id, 1, this.images[aux])
    .then(
      response => {
        this.unitService.uploadImages(this.unit.lessons[i].exercises[0].id, 2, this.images[aux+1])
          .then(
            response => {
              this.unitService.uploadImages(this.unit.lessons[i].exercises[0].id, 3, this.images[aux+2])
                .then(
                  response => {
                    console.log('Imagenens cargadas correctamente');
                  }
                )
            }
          )
      }
    )
    .catch(error => console.error('Error al subir las imagenes:' + error))
  }

  //
  check(event) {
    const alert = event.target.value;
    if (alert == "") {
      console.log("no data");
    }
  }


}
