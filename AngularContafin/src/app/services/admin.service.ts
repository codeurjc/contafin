import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParamsOptions } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from './utils.service';
import 'rxjs/Rx';
import { LoginService } from './login.service';
import { TokenStorageService } from './token-storage.service';
import { ErrorService } from './error.service';



const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(
		public utils: UtilsService, private errorService : ErrorService) { }

	async getUserData() : Promise<User[]>{

		let useData = null;
			 await this.utils.restService('/Admin/UserData', {
				method: 'get'
			  }).toPromise().then(
				(data : User[]) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}


	async exporData() {
		let useData = null;

			 await this.utils.restService('/Admin/UserData/', {
				queryString: 'Excel',
				method: 'get'
			  }).toPromise().then(
				(data) => {
					if (typeof data !== 'undefined' && data !== null) {
						console.log(data);

						let d = new Date();
						let year = d.getFullYear();
						let month = d.getMonth() + 1;
						let day = d.getDate();
						d.getUTCFullYear();
						
						let fileName="listaEstudiantes_"+day + "_" + month + "_" + year +".xlsx";

						var contentType = 'application/vnd.ms-excel'; 
						var blob = new Blob([data], { type: contentType });
						var a = document.createElement('a');
						a.href = URL.createObjectURL(blob);
						a.download = fileName;
						document.body.appendChild(a);
						a.click();
						document.body.removeChild(a);
					  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

}
