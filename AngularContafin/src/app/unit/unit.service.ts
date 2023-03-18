import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { Unit } from '../Interfaces/Unit/unit.model';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login/login.service';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class UnitsService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService,
		public user : LoginService
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


	async getUnit(id: number) {
		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: id ,
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

	//To know if a unit is completed
	/*isCompleted(id: number) {
		return this.http.get(BASE_URL + id + '/isCompleted')
			.catch(error => this.handleError(error));
	}*/



	async numberOfCompletedLessons2(id) {
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
	async addUnit(unit) {

		const userPass = this.user.user.email + ':' + this.user.pass;

		const headers ={
			headers: new HttpHeaders({
				'Authorization': 'Basic ' + utf8_to_b64(userPass),
				'X-Requested-With': 'XMLHttpRequest'
			})
		};

		let useData = null;
			 await this.utils.restServiceHeadersPost('/Unit/', {
				method: 'post',
				params: unit,
				headers: headers ,
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

function utf8_to_b64(str) {
	return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {//Mirar si esta bien
		return String.fromCharCode(<any>'0x' + p1);
	}));
}
