import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from '../../../src/app/services/utils.service';

const BASE_URL = environment.apiBase + '/User';


@Injectable()
export class UserService {

    public user: User;

    constructor(private http: HttpClient,public utils: UtilsService) {

    }

   async getUser(id: number) {
        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id,
				method: 'get',
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

    deleteAccount(id: number) {
        const headers = new HttpHeaders({
            'X-Requested-With': 'XMLHttpRequest'
        });
        return this.http.delete(BASE_URL + '/' + id, { withCredentials: true})
            .catch(error => this.handleError(error));

    }

    validation(id: number, pass: string) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.get(BASE_URL + '/' + id + '/Validation/' + pass, { withCredentials: true, headers })
            .catch(error => this.handleError(error));
    }

    async updateUser(id: number, updatedUser: User) {

        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id,
				method: 'put',
                params: updatedUser
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

    setGoal(id: number, goal: number) {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.put(BASE_URL + '/' + id + '/Goal', { withCredentials: true, headers })
            .catch(error => this.handleError(error));


    }

    async uploadImage(id: number, file) {

        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id + '/Photo',
				method: 'post',
                params: file
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

    async getProgress(id: number) {//No se si el tipo<> es el correcto

        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id + '/Progress',
				method: 'get',
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
        return ErrorObservable.create('Server error (' + error.status + '): ' + error.text());
    }

}