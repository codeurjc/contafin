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

	//Need the unit id and lesson id
	async getLesson(idLesson: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: 'Lesson/' + idLesson,
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

	changeNameLesson(idUnit: number, idLesson: number, name: string) {
		return this.http.put(BASE_URL + idUnit + '/Lesson/' + idLesson, name)
			.catch(error => this.handleError(error));
	}

	//To complete a lesson when the exercises are done.
	async completeLesson(idUnit: number, idLesson: number) {

		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: idUnit + '/Lesson/' + idLesson + '/Completed',
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
