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


	getUnit(id: number | string) {
		return this.http.get(BASE_URL + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	addUnit(unit: Unit) {
		return this.http.post(BASE_URL, unit)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	removeUnit(unit: Unit) {
		return this.http.delete(BASE_URL + unit.id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	updateUnit(unit: Unit) {
		return this.http.put(BASE_URL + unit.id, unit)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}