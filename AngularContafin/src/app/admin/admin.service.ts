import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';

const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService) { }

	async getUserData(page: number, size: number) {
		let useData = null;
			 await this.utils.restService('/Admin/UserData/', {
				queryString: '?page=' + page + '&size=' + size,
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

	//Export data to Excel
	async exporData() {
		let useData = null;
			 await this.utils.restService('/Admin/UserData/', {
				queryString: 'Excel',
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
