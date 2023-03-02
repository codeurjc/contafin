import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpParamsOptions } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from '../../../src/app/services/utils.service';
import { LoginService } from '../login/login.service';

const BASE_URL = environment.apiBase + '/User';


@Injectable()
export class UserService {

    constructor(private http: HttpClient,public utils: UtilsService, public user : LoginService) {

    }

   async getUser(id: number) {

	const userPass = this.user.user.email + ':' + this.user.pass;

        const headers = new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });

		let useData = null;
			 await this.utils.restServiceHeaders('/User', {
				queryString: '/' + id  ,
				method: 'get',
				headers: headers ,
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log("Usuario de getUser: " + JSON.stringify(data));
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

    async validation(id: number, pass: string) {
		const userPass = this.user.user.email + ':' + this.user.pass;

        const headers = new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });

		let useData = null;
			 await this.utils.restServiceHeaders('/User', {
				queryString: '/' + id + '/Validation/' + pass,
				method: 'get',
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


    async updateUser(id: number, updatedUser) {

		const userPass = this.user.user.email + ':' + this.user.pass;

        const headers ={
			headers: new HttpHeaders({
				'Authorization': 'Basic ' + utf8_to_b64(userPass),
				'X-Requested-With': 'XMLHttpRequest'
			})
		};

        let useData = null;
			 await this.utils.restServiceHeadersPost('/User/', {
				queryString: id,
				method: 'put',
                params: updatedUser,
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

    setGoal(id: number, goal: number) {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.put(BASE_URL + '/' + id + '/Goal', { withCredentials: true, headers })
            .catch(error => this.handleError(error));


    }

    async uploadImage(id: number, file) {

		const userPass = this.user.user.email + ':' + this.user.pass;

        const headers ={
			headers: new HttpHeaders({
				'Authorization': 'Basic ' + utf8_to_b64(userPass),
				'X-Requested-With': 'XMLHttpRequest'
			})
		};

        let useData = null;
			 await this.utils.restServiceHeadersPost('/User/', {
				queryString: id + '/Photo',
				method: 'post',
                params: file,
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

function utf8_to_b64(str) {
	return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {//Mirar si esta bien
		return String.fromCharCode(<any>'0x' + p1);
	}));
}