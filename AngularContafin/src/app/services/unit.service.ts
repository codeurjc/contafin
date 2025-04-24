import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { UtilsService } from './utils.service';
import 'rxjs/Rx';
import { Unit } from '../Interfaces/Unit/unit.model';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from './login.service';
import { ErrorService } from './error.service';

const BASE_URL = environment.apiBase + '/Unit/';

@Injectable()
export class UnitsService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService,
		public user : LoginService, private errorService : ErrorService
		) { }

	async getUnits() : Promise<Unit[]> {
		
		let useData = null;
			 await this.utils.restService('/Unit/', {
				method: 'get' 
			  }).toPromise().then(
				(data : Unit[]) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

	async getUnit(id: number) : Promise<Unit>{

		let useData = null;
			 await this.utils.restService('/Unit/', {
				queryString: id ,
				method: 'get'
			  }).toPromise().then(
				(data : Unit) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}


	async numberOfCompletedLessons2(id) : Promise<number> {

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
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

	async addUnit(unit) {

		let useData = null;
			 await this.utils.restService('/Unit/', {
				method: 'post',
				params: unit
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

	async deleteUnit(id) {

		let useData = null;
		await this.utils.restService('/Unit/', {
			queryString:'delete/' + id,
			method: 'get',
			params: id
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

	/*uploadImages(id: number, nImage: number, formData) {
		const headers = new HttpHeaders({
			'Accept': 'application/json',
			'X-Requested-With': 'XMLHttpRequest'
		});

		return this.http.post(BASE_URL + 'Exercise/' + id + '/' + nImage, formData, { withCredentials: true, headers })
			.toPromise()
			.then(response => response)
			.catch(error => this.errorService.handleError(error));
	}*/
}
