import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Unit } from '../Interfaces/Unit/unit.model';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class UnitsService {

	constructor(private http: HttpClient) { }

	getUnits() {
		return this.http.get(BASE_URL)
			.toPromise()
			.then(response => response)
			.catch(error => this.handleError(error));
	}


	getUnit(id: number) {
		return this.http.get(BASE_URL + id)
			.catch(error => this.handleError(error));
	}

	//To know if a unit is completed
	isCompleted(id: number) {
		return this.http.get(BASE_URL + id + '/isCompleted')
			.catch(error => this.handleError(error));
	}

	//Number of completed Lessons in a unit (int)
	numberOfCompletedLessons(id: number) {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.get(BASE_URL + id + '/numberOfCompletedLessons', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	numberOfCompletedLessons2() {
		const headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest'
		});
		return this.http.get(BASE_URL + 'numberOfCompletedLessons', { withCredentials: true, headers })
			.catch(error => this.handleError(error));
	}

	//Need the unit with its lessons and exercises
	addUnit(unit: Unit) {
		return this.http.post(BASE_URL, unit, { withCredentials: true })
			.catch(error => this.handleError(error));
	}

	uploadImages(id: number, nImage: number, formData) {
		const headers = new HttpHeaders({
			'Accept': 'application/json',
			'X-Requested-With': 'XMLHttpRequest'
		});

		return this.http.post(BASE_URL + 'Exercise/' + id + '/' + nImage, formData, { withCredentials: true, headers })
			.toPromise()
			.then(response => response)//DEJAR LOS THEN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			.catch(error => console.error(error));
	}

	changeNameUnit(id: number, name: string) {
		return this.http.put(BASE_URL + id, name)
			.catch(error => this.handleError(error));
	}

	deleteUnit(id: number) {
		return this.http.delete(BASE_URL + id)
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}



}
