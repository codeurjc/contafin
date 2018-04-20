import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { Answer } from '../Interfaces/Answer/answer.model';

const BASE_URL = environment.apiBase + '/Unit/Lessons/Exercises/';
const BASE_URL2 = environment.apiBase + '/Unit/';

@Injectable()
export class ExerciseService {

	constructor(private http: Http) { }

	getExercises() {
		return this.http.get(BASE_URL)
			.map(response => response.json().content)
			.catch(error => this.handleError(error));
	}

	//Need the unit id, lesson id and exercise id
	getExercise(idUnit: number, idLesson: number, idExercise: number) {
		return this.http.get(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	changeExercise(idUnit: number, idLesson: number, idExercise: number, exercise: Exercise) {
		return this.http.put(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise, exercise)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getAnswer(idUnit: number, idLesson: number, idExercise: number) {
		return this.http.get(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Answer')
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	changeAnswer(idUnit: number, idLesson: number, idExercise: number, result: string) {
		return this.http.put(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Answer', result)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	checkExercise(idUnit: number, idLesson: number, idExercise: number, result: string) {
		const headers = new Headers({
			'X-Requested-With': 'XMLHttpRequest'
		});
		const options = new RequestOptions({ withCredentials: true, headers });
		return this.http.put(BASE_URL2 + idUnit + '/Lesson/' + idLesson + '/Exercise/' + idExercise + '/Solution', result, options)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	deleteAllCompleted() {
		const headers = new Headers({
			'X-Requested-With': 'XMLHttpRequest'
		});
		const options = new RequestOptions({ withCredentials: true, headers });
		return this.http.delete(environment.apiBase + '/DeleteAllExercises', options)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
