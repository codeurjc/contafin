import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpParamsOptions } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from './utils.service';
import { LoginService } from './login.service';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { ErrorService } from './error.service';
import { TokenStorageService } from './token-storage.service';

const BASE_URL = environment.apiBase + '/User';


@Injectable()
export class UserService {

    constructor(private http: HttpClient,public utils: UtilsService, private tokenStorage: TokenStorageService, private sanitizer: DomSanitizer, private errorService : ErrorService) {

    }

   async getUser(id: number) : Promise<User> {

		let useData = null;
			 await this.utils.restService('/User', {
				queryString: '/' + id  ,
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log("Usuario de getUser: " + JSON.stringify(data));
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
    }

    async deleteAccount(id: number) {

		let useData = null;
			 await this.utils.restService('/User', {
				queryString: '/' + id ,
				method: 'delete'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;

    }

    async validation(id: number, param) {

		let useData = null;
			 await this.utils.restService('/User', {
				queryString: '/Validation/'+id,
				method: 'post',
				params : param
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


    async updateUser(id: number, updatedUser) : Promise<User> {

        let useData : User = null;
			 await this.utils.restService('/User/', {
				method: 'put',
                params: updatedUser
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));;
		return useData;
    }

    /*setGoal(id: number, goal: number) {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.put(BASE_URL + '/' + id + '/Goal', { withCredentials: true, headers })
		.catch(error => this.errorService.handleError(error));


    }*/

    /*async uploadImage(id: number, file) {

        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id + '/Photo',
				method: 'put',
                params: file
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
					let objectURL = 'data:image/jpeg;base64,' + file;       
        			this.tokenStorage.getLoginInfo().imageView = this.sanitizer.bypassSecurityTrustUrl(objectURL);
					this.tokenStorage.getUser().image = file;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));;
		return useData;
    }*/

    async getProgress(id: number) {

        let useData = null;
			 await this.utils.restService('/User/', {
				queryString: id + '/Progress',
				method: 'get'
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));;
		return useData;
    }

	async completeLesson(idLesson: number, points: number) : Promise<User>{

		let useData = null;
			 await this.utils.restService('/User/', {
				queryString: idLesson + '/Completed/' + points,
				method: 'post'
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

}