import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UnitsService } from '../unit.service';
import { NgbNavModule, NgbAccordionModule  } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder,FormControl, Validators, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-unit-creation',
  templateUrl: './unit-creation.component.html',
  styleUrls: ['./unit-creation.component.css']
})
export class UnitCreationComponent implements OnInit {

  alertDanger: boolean;
  images = [];
  exercises = {};
  lessons= [];
  unit;
  active = 1;
  kindList = [2];

  createUnitForm : FormGroup;

  constructor(private router: Router, private unitService: UnitsService, public formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.createUnitForm = this.formBuilder.group({
      name: []
    });

    for (var i = 0; i < 1; i++){
      this.addLesson();
    }

    console.log("Lecciones a revisar:" + JSON.stringify(this.lessons));
    console.log("Exercises a revisar:" + JSON.stringify(this.exercises));

    

    //this.defineUnit();
  }


public addLesson(): void{
  let id = this.getId();
  let exerciseList = [];
  for (var i = 0; i < 1; i++){
    exerciseList.push(this.addExercise(this.kindList[i]));
  }
  this.lessons.push({ id, exerciseList });

  this.createUnitForm.addControl('name_'+id, new FormControl(""));

}

public addExercise(kind): number{
  let id = this.getId();

  this.exercises[id] = kind;

  this.createUnitForm.addControl('statement_'+id, new FormControl(""));
  this.createUnitForm.addControl('texts_1_'+id, new FormControl(""));
  this.createUnitForm.addControl('texts_2_'+id, new FormControl(""));
  this.createUnitForm.addControl('texts_3_'+id, new FormControl(""));
  this.createUnitForm.addControl('images_1_'+id, new FormControl(null));
  this.createUnitForm.addControl('images_2_'+id, new FormControl(null));
  this.createUnitForm.addControl('images_3_'+id, new FormControl(null));
  this.createUnitForm.addControl('result_'+id, new FormControl(""));

  return id;

}

public getId(){
  return ((1 + Math.random()) * 0x10000) | 0;
}

public preSave(){
  const FORM_CONTROL = this.createUnitForm.controls;
  let name;
  let nameLesson;
  let statement;
  let texts1;
  let texts2;
  let texts3;
  let images1;
  let images2;
  let images3;
  let result;
  let exercises;
  let lessons = [];
  let unit;
  this.alertDanger = false;
  
  if(typeof FORM_CONTROL.name !== 'undefined' && typeof FORM_CONTROL.name.value !== 'undefined' && FORM_CONTROL.name.value !== null && FORM_CONTROL.name.value !== ''){
    name = FORM_CONTROL.name.value;
    console.log("Nombre Unidad:" + name);
  }else{
    this.alertDanger = true;
    console.log("1");
  }

  this.lessons.forEach((lesson)=>{

    exercises = [];

    nameLesson = FORM_CONTROL['name_' + lesson.id];

    if(typeof nameLesson !== 'undefined' && nameLesson.value !== 'undefined' && nameLesson.value !== null && nameLesson.value !== ''){
      nameLesson = nameLesson.value;
      console.log("Nombre Leccion:" + nameLesson);
    }else{
      this.alertDanger = true;
      console.log("2");
    }

    lesson.exerciseList.forEach((exercise)=>{

      statement = FORM_CONTROL['statement_'+exercise];
      texts1 = FORM_CONTROL['texts_1_'+exercise];
      texts2 = FORM_CONTROL['texts_2_'+exercise];
      texts3 = FORM_CONTROL['texts_3_'+exercise];
      images1 = FORM_CONTROL['images_1_'+exercise];
      images2 = FORM_CONTROL['images_2_'+exercise];
      images3 = FORM_CONTROL['images_3_'+exercise];
      result = FORM_CONTROL['result_'+exercise];

      if(typeof statement !== 'undefined' && statement.value !== 'undefined' && statement.value !== null && statement.value !== ''){
        statement = statement.value;
        console.log("Enunciado:" + statement);
      }else{
        this.alertDanger = true;
        console.log("3");
      }

      if(this.exercises[exercise] !== 2){
        if(typeof texts1 !== 'undefined' && texts1.value !== 'undefined' && texts1.value !== null && texts1.value !== '' ){
          texts1 = texts1.value;
        }else{
          this.alertDanger = true;
          console.log("4");
        }
        if(typeof texts2 !== 'undefined' && texts2.value !== 'undefined' && texts2.value !== null && texts2.value !== '' ){
          texts2 = texts2.value;
        }else{
          this.alertDanger = true;
          console.log("5");
        }
        if(typeof texts3 !== 'undefined' && texts3.value !== 'undefined' && texts3.value !== null && texts3.value !== '' ){
          texts3 = texts3.value;
        }else{
          this.alertDanger = true;
          console.log("6");
        }
      }else{
        texts1 = '';
        texts2 = '';
        texts3 = '';
      }


      if(this.exercises[exercise] === 1){
        if(typeof images1 !== 'undefined' && images1.value !== 'undefined' && images1.value !== null && images1.value !== '' ){
          images1 = images1.value;
        }else{
          this.alertDanger = true;
          console.log("7");
        }
        if(typeof images2 !== 'undefined' && images2.value !== 'undefined' && images2.value !== null && images2.value !== '' ){
          images2 = images2.value;
        }else{
          this.alertDanger = true;
          console.log("8");
        }
        if(typeof images3 !== 'undefined' && images3.value !== 'undefined' && images3.value !== null && images3.value !== '' ){
          images3 = images3.value;
        }else{
          this.alertDanger = true;
          console.log("9");
        }
      }else{
        images1 = null;
        images2 = null;
        images3 = null;
      }
 
      
      if(typeof result !== 'undefined' && result.value !== 'undefined' && result.value !== null && result.value !== ''){
        result = result.value;
      }else{
        this.alertDanger = true;
        console.log("10");
      }

      if(!this.alertDanger){
        let data = {
          kind : this.exercises[exercise],
          statement,
          texts : [texts1,texts2,texts3],
          image1 : images1,
          image2 : images2,
          image3 : images3,
          answer:{
            result
          } 
        }
  
        console.log("Ejercicio" + JSON.stringify(data));
        exercises.push(data);
      }

    });

    if(!this.alertDanger){
      let data = {
        name : nameLesson,
        exercises : exercises
      }
      console.log("Leccion" + JSON.stringify(data));
      lessons.push(data);
    }
  });

  if(!this.alertDanger){
    unit = {
      name,
      lessons
    }

    this.addUnit(unit);
  }
  

  console.log("Unidad actual a revisar:" + name);
  }

  //Other methods
  async addUnit(unit) {
    await this.unitService.addUnit(unit)
      .then(
        (unit : any) => {
          console.log(unit);
          this.unit = unit;
          this.router.navigate(['/Admin/Home']);
        }
      )
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
