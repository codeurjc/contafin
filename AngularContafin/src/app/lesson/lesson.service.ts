import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Lesson } from '../Interfaces/Lesson/lesson.model';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class LessonsService {

	constructor(private http: HttpClient) { }

	getLessons() {
		return this.http.get(BASE_URL + 'Lessons/')
			.catch(error => this.handleError(error));
	}

	//Get Lessons of the unit with its id
	getLessonsOfUnit(id: number) {
		return this.http.get(BASE_URL + id + '/Lesson/')
			.catch(error => this.handleError(error));
	}

	//Need the unit id and lesson id
	getLesson(idUnit: number, idLesson: number) {
		return this.http.get(BASE_URL + idUnit + '/Lesson/' + idLesson)
			.catch(error => this.handleError(error));
	}

	changeNameLesson(idUnit: number, idLesson: number, name: string) {
		return this.http.put(BASE_URL + idUnit + '/Lesson/' + idLesson, name)
			.catch(error => this.handleError(error));
	}

	//To complete a lesson when the exercises are done.
	completeLesson(idUnit: number, idLesson: number) {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.get(BASE_URL + idUnit + '/Lesson/' + idLesson + '/Completed', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	//To know if a Lesson is completed.
	isCompleted(idUnit: number, idLesson: number) {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.get(BASE_URL + idUnit + '/Lesson/' + idLesson + '/isCompleted', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	isCompleted2(idUnit: number) {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.get(BASE_URL + idUnit + '/Lessons/Completed', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}
}
