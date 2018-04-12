import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Lesson } from '../Interfaces/Lesson/lesson.model';

const BASE_URL = environment.apiBase + '/Lessons/';
const BASE_URL2 = environment.apiBase + '/Unit/';

@Injectable()
export class LessonsService {

	constructor(private http: Http) { }

	getLessons() {
		return this.http.get(BASE_URL)
			.map(response => response.json().content)
			.catch(error => this.handleError(error));
	}

    //Get Lessons of the unit with its id
    getUnitAndLessons(id:number){
		return this.http.get(BASE_URL2 + id + '/Lesson/')
			.map(response => response.json().content)
			.catch(error => this.handleError(error));
	}

    //Need the unit id and lesson id
	getLesson(idUnit: number, idLesson:number) {
		return this.http.get(BASE_URL2 + idUnit +'/Lesson/'+idLesson )
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	changeNameLesson(idUnit: number, idLesson:number, name:string) {
		return this.http.put(BASE_URL2 + idUnit +'/Lesson/'+idLesson, name)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
