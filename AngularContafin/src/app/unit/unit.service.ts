import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Unit } from '../Interfaces/Unit/unit.model';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class UnitsService {

	constructor(private http: Http) { }

	getUnits() {
		return this.http.get(BASE_URL)
			.map(response => response.json().content)
			.catch(error => this.handleError(error));
	}


	getUnit(id: number) {
		return this.http.get(BASE_URL + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	//To know if a unit is completed
	isCompleted(id: number) {
		return this.http.get(BASE_URL + id + '/isCompleted')
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	//Number of completed Lessons in a unit (int)
	numberOfCompletedLessons(id: number) {
		return this.http.get(BASE_URL + id + '/numberOfCompletedLessons')
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	//Need the unit with its lessons and exercises
	addUnit(unit: Unit) {
		return this.http.post(BASE_URL, unit)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	//No va
	/*uploadImages(id: number, images: Array<Images>) {
		return this.http.post(BASE_URL+ id +'/Images', images)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}*/

	changeNameUnit(id: number, name:string) {
		return this.http.put(BASE_URL + id, name)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	deleteUnit(id:number) {
		return this.http.delete(BASE_URL + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
