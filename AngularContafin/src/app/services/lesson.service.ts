import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { Lesson } from '../Interfaces/Lesson/lesson.model';
import { ErrorService } from './error.service';
import { User } from '../Interfaces/User/user.model';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class LessonsService {

	constructor(
		public utils: UtilsService, private errorService : ErrorService
		) { }

	async getLesson(idLesson: number) : Promise<Lesson> {

		let useData : Lesson = null;
			 await this.utils.restService('/Lesson/', {
				queryString: idLesson,
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

	async isCompleted2(id: number) : Promise<boolean[]> {

		let useData = null;
			 await this.utils.restService('/Lesson/', {
				queryString: id + '/AllCompleted',
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

	async UnitCompletedForHome() {

		let useData = null;
			 await this.utils.restService('/Lesson/', {
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

}
