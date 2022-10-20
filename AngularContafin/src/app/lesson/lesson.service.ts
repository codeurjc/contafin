import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { Lesson } from '../Interfaces/Lesson/lesson.model';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class LessonsService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService
		) { }

	getLessons() {
		return this.http.get(BASE_URL + 'Lessons/')
			.catch(error => this.handleError(error));
	}

	//Get Lessons of the unit with its id
	async getLessonsOfUnit(id: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: id + '/Lesson/',
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

	async isCompleted2(id: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: id + '/Lessons/Completed',
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

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}
}
