import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import 'rxjs/Rx';

const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(private http: Http) { }

	getUserData(page: number, size: number) {
		const options = new RequestOptions({ withCredentials: true });
		return this.http.get(BASE_URL + '?page=' + page + '&size=' + size, options)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	//Export data to Excel
	exporData() {
		return this.http.get(BASE_URL + 'Excel')
			.map(response => response.blob())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
