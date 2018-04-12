import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';

const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(private http: Http) { }

	getUserData(page:number, size:number) {
		return this.http.get(BASE_URL +'?page=' + page + '&size=' + size)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

    //Export data to Excel
    exporData(){
        return this.http.get(BASE_URL + 'Excel')
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }
	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
