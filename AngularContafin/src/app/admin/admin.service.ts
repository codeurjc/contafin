import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import 'rxjs/Rx';

const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(private http: HttpClient) { }

	getUserData(page: number, size: number) {
		//const options = new RequestOptions({ withCredentials: true });
		return this.http.get(BASE_URL + '?page=' + page + '&size=' + size, { withCredentials: true })//return this.http.get(BASE_URL + '?page=' + page + '&size=' + size, options)
			.catch(error => this.handleError(error));
	}

	//Export data to Excel
	exporData() {
		return this.http.get(BASE_URL + 'Excel', { responseType: "blob" }) //.map(response => response.blob())
			.catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}
}
