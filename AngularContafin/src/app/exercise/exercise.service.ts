import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { UtilsService } from '../../../src/app/services/utils.service';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { Answer } from '../Interfaces/Answer/answer.model';

const BASE_URL = environment.apiBase + '/Unit/Lessons/Exercises/';
const BASE_URL2 = environment.apiBase + '/Unit/';

@Injectable()
export class ExerciseService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService
		) { }

	getExercises() {
		return this.http.get(BASE_URL)
			.catch(error => this.handleError(error));
	}

	//Need the unit id, lesson id and exercise id
	async getExercise(idUnit: number, idLesson: number, idExercise: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise,
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  );
		return useData;
	}

	//Same as getExercise but with promise
	async getOneExercise(idUnit: number, idLesson: number, idExercise: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise,
				method: 'get'
			  }).toPromise().then(
				(data) => {
					console.log("Dato ejercicio:" + data);
				  if (typeof data !== 'undefined' && data !== null) {
					useData = data;
				  }
				}
			  );
		return useData;
	}

	changeExercise(idUnit: number, idLesson: number, idExercise: number, exercise: Exercise) {
		return this.http.put(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise, exercise)
			.catch(error => this.handleError(error));
	}

	getAnswer(idUnit: number, idLesson: number, idExercise: number) {
		return this.http.get(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Answer')
			.catch(error => this.handleError(error));
	}

	changeAnswer(idUnit: number, idLesson: number, idExercise: number, result: string) {
		return this.http.put(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Answer', result)
			.catch(error => this.handleError(error));
	}

	async checkExercise(idUnit: number, idLesson: number, idExercise: number, result) {
		
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Solution', 
				params : result,
				method: 'put'
			  }).toPromise().then(
				(data) => {
					console.log("Solucion:" + data);
				  if (typeof data !== 'undefined' && data !== null) {
					useData = data;
				  }
				}
			  );
		return useData;
		
	}

	deleteAllCompleted() {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.delete(environment.apiBase + '/DeleteAllExercises', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}
}
