import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { Unit } from '../Interfaces/Unit/unit.model';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class UnitsService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService
		) { }

	async getUnits() {
		let useData = null;
			 await this.utils.restService('/Unit/', {
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

	async numberOfCompletedLessons2(id: Number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: id + '/numberOfCompletedLessons',
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
